package com.ichinait.food.controller.type;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Type;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.TypePlus;
import com.ichinait.food.service.sample.SampleService;
import com.ichinait.food.service.type.TypeService;
import com.ichinait.food.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by ichinait on 2016/4/8.
 * 样品种类管理
 */
@Controller
@RequestMapping("type")
public class TypeController extends BaseController<Type,TypeService>{
    private Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;
    @Autowired
    private SampleService sampleService;
    


    /**
     *
     * 通过父id和类型查询type
     * @param id
     * @param type
     * @return
     */

    @RequestMapping("queryByParentId")
    @ResponseBody
    public Map<String,Object> queryTypeByParentId(String id,int type){
        logger.info("======================通过父ID和类型查询type================={},{}",id,type);
        Map<String,Object> result = Maps.newHashMap();
        List<Type> types = typeService.queryTypeByParentId(id,type);
        result.put("types",types);
        logger.info("======================通过父ID和类型查询type结果================={}", JsonMapper.nonEmptyMapper().toJson(result));
        return result;
    }
    
    @RequestMapping("toQueryPage")
    public String toQueryPage(HttpServletRequest request){
    	List<Type> types  = typeService.queryTypeByParentId("0",1);
    	request.setAttribute("types",types);
    	return "/type/type_list";
    }
    
    @RequestMapping("toAddPage")
    public String toAddPage(HttpServletRequest request){
    	List<Type> types  = typeService.queryTypeByParentId("0",1);
    	request.setAttribute("types",types);
    	return "/type/type_add";
    }
   
   	/*
   	 * 以下是新写的。。。。。。。。。。。。。。。。。
   	 */
   
    
    @RequestMapping("save")
	@ResponseBody
	public Map<String,Object> save(Type type){
		Map<String,Object> result = Maps.newHashMap();
        logger.info("保存样品分类");
        User user = super.getSessionUser();
        type.setOperator(user.getId());
        type.setType(1);
        typeService.saveOrUpdateType(type);
		result.put("success",Constant.EXEC_SUCCESS);
		return result;
	}

    
    @RequestMapping("queryType")
    @ResponseBody
   	public Map<String,Object> queryType(@RequestParam(value="curr",defaultValue="1",required=true)int curror,String data){
       	logger.info("查询样品分类列表：{}",data);
   		Map<String,Object> result = Maps.newHashMap();
   		JavaType type = JsonMapper.nonEmptyMapper().constructMapType(Map.class, String.class, Object.class);
   		Map<String,Object> params = JsonMapper.nonEmptyMapper().fromJson(data, type);
   		PageInfo<TypePlus> pageInfo = typeService.queryTypeByConditions(curror, params);
   		super.setSuccess(pageInfo, result);
   		return result;
   	}
    

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> deleteType(String typeId){
    	Map<String,Object> result = Maps.newHashMap();
    	if(!sampleService.hasRecord(typeId)){
            typeService.deleteType(typeId);
            result.put("success",Constant.EXEC_SUCCESS);
        }else{
            result.put("success",Constant.EXEC_FAILED);
        }
    	return result;
}





    @RequestMapping("toEditPage")
    public String toEditPage(String id,HttpServletRequest request){
        Type type = typeService.queryTypeById(id);
        request.setAttribute("type",type);
        List<Type> types  = typeService.queryTypeByParentId("0",1);
        request.setAttribute("types",types);
        return "/type/type_edit";
    }

    /**
     *
     * 验证code是否可以使用
     * @param code
     * @return flag 为true 表示可以使用该code
     */

    @RequestMapping("validate")
    @ResponseBody
    public Object validateCode(String code){
        Map<String,Object> result = Maps.newHashMap();
        boolean flag = typeService.queryTypeByCode(code);
        result.put("flag",!flag);
        return result;
    }
}
