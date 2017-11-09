package com.ichinait.food.controller.type;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Type;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.service.type.TypeService;
import com.ichinait.food.util.JsonMapper;
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
 * Created by ichinait on 2016/4/8.
 */
@Controller
@RequestMapping("serviceType")
public class TypeController2 extends BaseController<Type, TypeService> {
    private Logger logger = LoggerFactory.getLogger(TypeController2.class);

    @Autowired
    private TypeService typeService;


    /**
     * 通过父id和类型查询type
     *
     * @param id
     * @param type
     * @return
     */

    @RequestMapping("queryByParentId")
    @ResponseBody
    public Map<String, Object> queryTypeByParentId(String id, int type) {
        logger.info("======================通过父ID和类型查询type================={},{}", id, type);
        Map<String, Object> result = Maps.newHashMap();
        List<Type> types = typeService.queryTypeByParentId(id, type);
        result.put("types", types);
        logger.info("======================通过父ID和类型查询type结果================={}", JsonMapper.nonEmptyMapper().toJson(result));
        return result;
    }


    @RequestMapping("toQuery2Page")
    public String toQuery2Page(HttpServletRequest request) {
        List<Type> types = typeService.queryTypeByParentId("0", 2);
        request.setAttribute("types", types);
        return "/type/type_list2";
    }

    @RequestMapping("toAdd2Page")
    public String toAdd2Page(HttpServletRequest request) {
        return "/type/type_add2";
    }
       /*
   	 * 以下是新写的。。。。。。。。。。。。。。。。。
   	 */

    /**
     * @param curror
     * @param id
     * @return
     */
    @RequestMapping("serviceType")
    @ResponseBody
    public Map<String, Object> serviceType(@RequestParam(value = "curr", defaultValue = "1", required = true) int curror, String id) {
        logger.info("查询服务类型列表：{}", id);
        Map<String, Object> result = Maps.newHashMap();
        //JavaType type = JsonMapper.nonEmptyMapper().constructMapType(Map.class, String.class, Object.class);
        //Map<String,Object> params = JsonMapper.nonEmptyMapper().fromJson(data, type);
        PageInfo<Type> pageInfo = typeService.queryServiceType(curror, id);
        super.setSuccess(pageInfo, result);
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> deleteType(String typeId) {
        Map<String, Object> result = Maps.newHashMap();
        typeService.deleteType(typeId);
        result.put("success", Constant.EXEC_SUCCESS);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> saveOrUpdate(Type type) {
        logger.debug("save servicetype {}", JsonMapper.nonEmptyMapper().toJson(type));
        Map<String, Object> result = Maps.newHashMap();
        User user = super.getSessionUser();
        type.setType(2);
        type.setOperator(user.getId());
        typeService.saveOrUpdate(type);
        result.put("success", Constant.EXEC_SUCCESS);
        return result;
    }


    @RequestMapping("toEditPage")
    public String toEditPage(String id, HttpServletRequest request) {
        Type type = typeService.queryTypeById(id);
        request.setAttribute("type", type);
        List<Type> types = typeService.queryTypeByParentId("0", 1);
        request.setAttribute("types", types);
        return "/type/type_edit2";
    }
    @RequestMapping("queryTypeByName")
    @ResponseBody
    public Map<String,Object> queryTypeByName(String name){
        Map<String, Object> result = Maps.newHashMap();
        List<Type> types = typeService.queryServiceTypeByName(name);
        result.put("has", true);
        if(CollectionUtils.isEmpty(types)){
            result.put("has", false);
        }
        result.put("success", Constant.EXEC_SUCCESS);
        return result;
    }

}
