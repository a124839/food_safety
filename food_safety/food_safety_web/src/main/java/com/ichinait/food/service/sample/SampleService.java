package com.ichinait.food.service.sample;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.ichinait.food.cache.SampleCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Sample;
import com.ichinait.food.db.entity.SampleExample;
import com.ichinait.food.db.entity.SampleIndicator;
import com.ichinait.food.db.entity.SampleIndicatorExample;
import com.ichinait.food.db.entity.plus.SamplePlus;
import com.ichinait.food.db.mapper.SampleIndicatorMapper;
import com.ichinait.food.db.mapper.SampleMapper;
import com.ichinait.food.db.mapper.plus.SampleIndicatorMapperPlus;
import com.ichinait.food.db.mapper.plus.SampleMapperPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ichinait on 2016/3/15.
 */
@Service
public class SampleService {
    @Autowired
    private SampleMapperPlus sampleMapperPlus;

    @Autowired
    private SampleMapper sampleMapper;
    @Autowired
    private SampleIndicatorMapper sampleIndicatorMapper;
    @Autowired
    private SampleIndicatorMapperPlus sampleIndicatorMapperPlus;
    
    public List<Sample> querySamplesNoPage(){
        SampleExample example = new SampleExample();
        example.createCriteria().andStatusEqualTo(Constant.DATA_VALID_STATE);
        example.setOrderByClause("code desc");
        return sampleMapper.selectByExample(example);
    }
    
    /**
     * 分页查询样品列表
     * @description: 
     * @param curror
     * @param params
     * @return
     * @author:zhangbo@ichinait.com
     * @version 1.0
     * @date 2016年3月16日 下午4:57:51
     */
    public PageInfo<Sample> querySampleByConditions(int curror,Map<String,Object> params){
		PageHelper.startPage(curror, Constant.PAGE_LIMIT);
		List<Sample> samples = sampleMapperPlus.selectSampleByConditions(params);
		PageInfo<Sample> pageInfo = new PageInfo<Sample>(samples);
		return pageInfo;
	}
    
    /**
     * 删除样品
     * @description: 
     * @param sampleId
     * @return
     * @author:zhangbo@ichinait.com
     * @version 1.0
     * @date 2016年3月16日 下午4:58:07
     */
    public boolean deleteSample(String sampleId){
    	Sample s = new Sample();
    	s.setId(sampleId);
    	s.setStatus(Constant.DATA_INVALID_STATE);
        SampleCache.getInstance().remove(s);
    	return sampleMapper.updateByPrimaryKeySelective(s)>0;
    }
    
    

    public boolean saveOrUpdate(Sample sample){
        List<SampleIndicator> indicators = sample.getSampleIndicators();
        boolean flag = false;
        sample.setUt(new Date());
        if(Strings.isNullOrEmpty(sample.getId())){
            String sampleId = UUID.randomUUID().toString();
            sample.setCt(new Date());
            sample.setStatus(Constant.DATA_VALID_STATE);
            sample.setId(sampleId);
             if(!CollectionUtils.isEmpty(indicators)){
                 for(SampleIndicator indicator:indicators){
                     indicator.setId(UUID.randomUUID().toString());
                     indicator.setSampleId(sampleId);
                 }
                 sampleIndicatorMapperPlus.batchInsert(indicators);
             }
            flag = sampleMapper.insert(sample)>0;
            SampleCache.getInstance().add(sample);//没出错，就更新缓存吧
        }else{
            for(SampleIndicator indicator:indicators){
                if(Strings.isNullOrEmpty(indicator.getId())){//修改的时候也可能会添加指标
                    indicator.setId(UUID.randomUUID().toString());
                    indicator.setSampleId(sample.getId());
                    sampleIndicatorMapper.insert(indicator);
                }else{
                    sampleIndicatorMapper.updateByPrimaryKeySelective(indicator);
                }
            }
            flag = sampleMapper.updateByPrimaryKeySelective(sample)>0;
            SampleCache.getInstance().update();
        }
        return  flag;
    }

    /**
     * 查询样品信息
     * @param id
     * @return
     */
    public SamplePlus querySampleById(String id){
    	SamplePlus samplePlus = sampleMapperPlus.selectSampleById(id);
        SampleIndicatorExample example = new SampleIndicatorExample();
        example.setOrderByClause("indicator_name");
        example.createCriteria().andSampleIdEqualTo(id);
        List<SampleIndicator> indicators = sampleIndicatorMapper.selectByExample(example);
        if(samplePlus != null){
        	samplePlus.setSampleIndicators(indicators);
        }
        return samplePlus;
    }

    /**
     * 删除样品指标
     * @param id 指标id
     */
    public void deleteSampleIndicator(String id){
        sampleIndicatorMapper.deleteByPrimaryKey(id);
    }


    /**
     * 判断要删除的样品类型是否已经被使用
     * @param typeId
     * @return
     */
    public boolean hasRecord(String typeId){
        SampleExample example = new SampleExample();
        SampleExample.Criteria criteria =  example.createCriteria();
        criteria.andCategoryLv1IdEqualTo(typeId).andStatusEqualTo(Constant.DATA_VALID_STATE);
        example.or().andCategoryLv2IdEqualTo(typeId).andStatusEqualTo(Constant.DATA_VALID_STATE);
        return sampleMapper.selectByExample(example).size()>0;

    }

}
