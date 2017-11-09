package com.ichinait.food.service.algorithm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.*;
import com.ichinait.food.db.mapper.AlgorithmMapper;
import com.ichinait.food.db.mapper.AlgorithmParamsMapper;
import com.ichinait.food.db.mapper.plus.AlgorithmMapperPlus;
import com.ichinait.food.dto.algorithm.AlgorithmAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ichinait on 2016/3/21.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AlgorithmService {
    @Autowired
    private AlgorithmMapper algorithmMapper;
    @Autowired
    private AlgorithmMapperPlus algorithmMapperPlus;

    @Autowired
    private AlgorithmParamsMapper algorithmParamsMapper;

    /**
     * 分页查询算法
     * @param curror
     * @param params
     * @return
     */
    public PageInfo<Algorithm> queryAlgorithmByConditions(int curror, Map<String,Object> params){
        PageHelper.startPage(curror, Constant.PAGE_LIMIT);
        PageHelper.orderBy("a.ct desc");
        List<Algorithm> list = algorithmMapperPlus.selectAlgorithmByCondition(params);
        PageInfo<Algorithm> pageInfo = new PageInfo<Algorithm>(list);
        return pageInfo;
    }

    /**
     * 停用算法
     * @param id
     * @return
     */
    public boolean updateAlgorithm(String id){
        AlgorithmWithBLOBs algorithm = new AlgorithmWithBLOBs();
        algorithm.setId(id);
        algorithm.setStatus(Constant.DATA_INVALID_STATE);
        return algorithmMapper.updateByPrimaryKeySelective(algorithm)>0;
    }


    public void saveOrUpdate(AlgorithmAddDTO dto)throws Exception{
        if(Strings.isNullOrEmpty(dto.getId())){
            saveAlgorithm(dto);
        }else{
            updateAlgorithm(dto);
        }
    }

    /**
     * 修改算法与算法参数
     * @param dto
     *
     */
    private void updateAlgorithm(AlgorithmAddDTO dto){
        AlgorithmWithBLOBs algorithm = new AlgorithmWithBLOBs();
        algorithm.setId(dto.getId());
        algorithm.setCategory(Constant.CUSTOMER_ALGORITHM);
        algorithm.setOperator(dto.getOperator());
        algorithm.setSampleCode(dto.getSampleCode());
        algorithm.setExample(dto.getExample());
        if(dto.getAttachmentId() != null){
            algorithm.setAttachmentId(dto.getAttachmentId());
        }
        algorithm.setType(dto.getAlgorithmType());
        algorithm.setName(dto.getAlgorithmName());
        algorithm.setMemo(dto.getMemo());
        algorithm.setUt(new Date());
        algorithmMapper.updateByPrimaryKeySelective(algorithm);
        List<AlgorithmParams> params = dto.getAlgorithmParams();
        for(AlgorithmParams param:params){
            if(Strings.isNullOrEmpty(param.getId())){
                param.setId(UUID.randomUUID().toString());
                param.setAlgorithmId(dto.getId());
                algorithmParamsMapper.insert(param);
            }else {
                algorithmParamsMapper.updateByPrimaryKeySelective(param);
            }
        }


    }

    private void saveAlgorithm(AlgorithmAddDTO dto){
        String algorithmId = UUID.randomUUID().toString();
        AlgorithmWithBLOBs algorithm = new AlgorithmWithBLOBs();
        algorithm.setId(algorithmId);
        algorithm.setCategory(Constant.CUSTOMER_ALGORITHM);
        algorithm.setOperator(dto.getOperator());
        algorithm.setSampleCode(dto.getSampleCode());
        algorithm.setExample(dto.getExample());
        algorithm.setType(dto.getAlgorithmType());
        algorithm.setName(dto.getAlgorithmName());
        if(dto.getAttachmentId() != null){
            algorithm.setAttachmentId(dto.getAttachmentId());
        }
        algorithm.setStatus(Constant.DATA_VALID_STATE);
        algorithm.setCt(new Date());
        algorithm.setUt(new Date());
        algorithm.setMemo(dto.getMemo());
        algorithmMapper.insert(algorithm);
        List<AlgorithmParams> params = dto.getAlgorithmParams();
        for(AlgorithmParams param:params){
            param.setId(UUID.randomUUID().toString());
            param.setAlgorithmId(algorithmId);
            algorithmParamsMapper.insert(param);//这里使用频率较低，参数个数较少，不会超过10个，就不用批量添加了
        }
    }

    public Algorithm queryAlgorithmById(String id){
        return algorithmMapper.selectByPrimaryKey(id);
    }

    public List<AlgorithmParams> queryAlgorithmParams(String id){
        AlgorithmParamsExample example = new AlgorithmParamsExample();
        example.createCriteria().andAlgorithmIdEqualTo(id);
        return algorithmParamsMapper.selectByExample(example);
    }


    public List<Algorithm> queryAlgorithmNoPage(int category){
        AlgorithmExample example = new AlgorithmExample();
        example.createCriteria().andStatusEqualTo(Constant.DATA_VALID_STATE).andCategoryEqualTo(category);
        return algorithmMapper.selectByExample(example);
    }


    public List<AlgorithmParams> queryAlgorithmParamsByAlgorithmId(String id){
        AlgorithmParamsExample example = new AlgorithmParamsExample();
        example.createCriteria().andAlgorithmIdEqualTo(id);
       return algorithmParamsMapper.selectByExample(example);
    }

    public boolean deleteParam(String id){
        return algorithmParamsMapper.deleteByPrimaryKey(id)>0;
    }

}
