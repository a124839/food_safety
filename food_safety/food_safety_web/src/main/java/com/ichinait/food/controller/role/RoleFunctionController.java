package com.ichinait.food.controller.role;

import com.google.common.base.Throwables;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Role;
import com.ichinait.food.db.entity.plus.RolePlus;
import com.ichinait.food.service.role.RoleFunctionService;
import com.ichinait.food.service.role.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

/**
 * Created by xiao on 2016/04/18
 */

@Controller
@RequestMapping("roleFunctionManage")
public class RoleFunctionController extends BaseController<Role, RoleService> {
    private static Logger logger = LoggerFactory.getLogger(RoleFunctionController.class);

    @Resource
    private RoleFunctionService roleFunctionService;


    /**
     * 跳转到角色授权页面
     * @return
     */
    @RequestMapping(value="toAuthorizationPage/{roleId}",method=RequestMethod.GET)
    public String toAuthorizationPage(Model model, @PathVariable String roleId){
        logger.info("角色授权：{}", roleId);
        RolePlus rolePlus = roleFunctionService.selectRoleInfoByRoleId(roleId);
        model.addAttribute("roleFunc", rolePlus);

        return "/role/role_function";
    }


    /**
     * 角色授权
     * @return
     */
    @RequestMapping(value="roleAuthorization",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> roleAuthorization(RolePlus rolePlus){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            boolean flag = roleFunctionService.saveRoleAuthorization(rolePlus);
            if(flag){
                result.put("success", Constant.EXEC_SUCCESS);
            }else{
                result.put("success", Constant.EXEC_FAILED);
            }

        } catch (Exception e) {
            logger.error("角色授权失败：{}", Throwables.getStackTraceAsString(e));
            super.setError(e,result);
        }
        return result;
    }


}
