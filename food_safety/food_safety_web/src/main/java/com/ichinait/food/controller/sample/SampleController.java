package com.ichinait.food.controller.sample;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ichinait.food.cache.SampleCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Sample;
import com.ichinait.food.db.entity.SampleIndicator;
import com.ichinait.food.db.entity.Type;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.SamplePlus;
import com.ichinait.food.service.sample.SampleService;
import com.ichinait.food.service.type.TypeService;
import com.ichinait.food.util.JsonMapper;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by ichinait on 2016/3/15.
 */
@Controller
@RequestMapping("sample")
public class SampleController extends BaseController<Sample,SampleService>{
	private static Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	private SampleService sampleService;
	@Autowired
	private TypeService typeService;
	
	@RequestMapping("toSamplePage")
    public String toSamplePage(){
        return "/sample/sample_list";
    }
    
    
    @RequestMapping("queryAll")
	@ResponseBody
	public Map<String,Object> querySampleNoPage(){
		Map<String,Object> result = Maps.newHashMap();
		result.put("samples", SampleCache.getInstance().getCachedSamples());
		return result;
	}
    
    
    @RequestMapping("querySample")
	@ResponseBody
	public Map<String,Object> querySample(@RequestParam(value="curr",defaultValue="1",required=true)int curror,String data){
    	logger.info("查询样品列表：{}",data);
		Map<String,Object> result = Maps.newHashMap();
		JavaType type = JsonMapper.nonEmptyMapper().constructMapType(Map.class, String.class, Object.class);
		Map<String,Object> params = JsonMapper.nonEmptyMapper().fromJson(data, type);
		PageInfo<Sample> pageInfo = sampleService.querySampleByConditions(curror, params);
		super.setSuccess(pageInfo, result);
		return result;
	}
    
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> deleteSample(String sampleId){
    	Map<String,Object> result = Maps.newHashMap();
    	sampleService.deleteSample(sampleId);
    	result.put("success",Constant.EXEC_SUCCESS);
    	return result;
    }

	@RequestMapping("toAddPage")
	public String toAddPage(HttpServletRequest request){
		List<Type> types = typeService.queryTypeByParentId("0", 1);
		if(!CollectionUtils.isEmpty(types)){
			List<Type> lv2Types = typeService.queryTypeByParentId(types.get(0).getId(), 1);
			request.setAttribute("lv2",lv2Types);
		}
		request.setAttribute("lv1",types);
		return "/sample/sample_add";
	}


	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> save(Sample sample,String indicators){
        JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
		Map<String,Object> result = Maps.newHashMap();
        logger.info("保存样品，样品指标：{}",indicators);
        User user =  (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        sample.setOperator(user.getId());
        JavaType javaType = jsonMapper.constructCollectionType(List.class,SampleIndicator.class);
        List<SampleIndicator> sampleIndicators = jsonMapper.fromJson(indicators,javaType);
        sample.setSampleIndicators(sampleIndicators);
		sampleService.saveOrUpdate(sample);
		result.put("success",Constant.EXEC_SUCCESS);
		return result;
	}

    @RequestMapping("edit")
    public String toEditPage(String id, HttpServletRequest request){
    	SamplePlus sample = sampleService.querySampleById(id);
        request.setAttribute("sample",sample);
        List<Type> types = typeService.queryTypeByParentId("0", 1);
		List<Type> lv2Types = typeService.queryTypeByParentId(sample.getCategoryLv1Id(), 1);
		request.setAttribute("lv1",types);
		request.setAttribute("lv2",lv2Types);
        return "/sample/sample_edit";
    }


	@RequestMapping("deleteIndicator")
	@ResponseBody
	public Map<String,Object> deleteIndicator(String id){
        logger.info("删除样品指标:{}",id);
		Map<String,Object> result = Maps.newHashMap();
        sampleService.deleteSampleIndicator(id);
		return result;
	}

    @RequestMapping("view")
    public String toViewPage(String id, HttpServletRequest request){
        SamplePlus sample = sampleService.querySampleById(id);
        request.setAttribute("sample",sample);
        return "/sample/sample_view";
    }
    
}
