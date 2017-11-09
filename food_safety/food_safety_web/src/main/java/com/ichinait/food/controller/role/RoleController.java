package com.ichinait.food.controller.role;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Role;
import com.ichinait.food.service.role.RoleService;
import com.ichinait.food.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiao on 2016/04/15.
 */

@Controller
@RequestMapping("roleManage")
public class RoleController extends BaseController<Role, RoleService> {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;


    /**
     * 跳转到角色列表
     * @return
     */
    @RequestMapping(value="toQueryPage",method=RequestMethod.GET)
    public String toRoleManagePage(){
        return "/role/role_list";
    }


    /**
     * 模糊查询角色
     * @param curror
     * @param roleName
     * @return
     */
    @RequestMapping(value="query",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryRolesByConditions(
            @RequestParam(value="curr",defaultValue="1",required=true)int curror,
            @RequestParam(value="roleName",required=false)String roleName
    ){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            Role conditions = new Role();
            if(!StringUtils.isEmpty(roleName)){
                conditions.setRoleName(roleName);
            }
            PageInfo<Role> pageInfo = roleService.queryAllRoleByRoleName(curror, roleName);
            super.setSuccess(pageInfo,result);
        } catch (Exception e) {
            logger.error("查询角色列表失败：{}", Throwables.getStackTraceAsString(e));
            super.setError(e,result);
        }
        return result;
    }


    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value="add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addRole(Role role){
        logger.info("=========添加角色:{}===============", JsonMapper.nonEmptyMapper().toJson(role));
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            boolean flag = roleService.addRole(role);
            if(flag){
                result.put("success", Constant.EXEC_SUCCESS);
            }else{
                result.put("success", Constant.EXEC_FAILED);
            }
        } catch (Exception e) {
            logger.error("添加角色失败：{}",Throwables.getStackTraceAsString(e));
            super.setError(e, result);
        }

        return result;
    }


    /**
     * 添加角色校验
     * @param roleName
     * @return
     */
    @RequestMapping(value="validateRole",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> validateRole(String roleName){
        logger.info("=========角色校验:{}===============", roleName);
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            boolean flag = roleService.selectValidateRoleByRoleName(roleName);
            if(flag){
                result.put("success", Constant.EXEC_FAILED);
            } else {
                result.put("success", Constant.EXEC_SUCCESS);
            }
            result.put("flag", !flag);
        } catch (Exception e) {
            logger.error("校验角色失败：{}",Throwables.getStackTraceAsString(e));
            super.setError(e, result);
        }

        return result;
    }


    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value="update",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateRole(Role role){
        logger.info("=========编辑角色:{}===============", JsonMapper.nonEmptyMapper().toJson(role));
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            boolean flag = roleService.updateRoleByRoleId(role);
            if(flag){
                result.put("success", Constant.EXEC_SUCCESS);
            }else{
                result.put("success", Constant.EXEC_FAILED);
            }
        } catch (Exception e) {
            logger.error("编辑角色失败：{}",Throwables.getStackTraceAsString(e));
            super.setError(e, result);
        }

        return result;
    }


    @RequestMapping(value="delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteRole(@RequestParam(value="roleId") String roleId){
        logger.info("=========删除角色:{}===============",roleId);
        Map<String,Object> result = new HashMap<String,Object>();
        Role role = new Role();
        role.setId(roleId);
        role.setStatus(Constant.DATA_INVALID_STATE);
        try {
            boolean flag = roleService.updateRole(role);
            if(flag){
                result.put("success", Constant.EXEC_SUCCESS);
            }else{
                result.put("success", Constant.EXEC_FAILED);
            }
        } catch (Exception e) {
            logger.error("删除角色失败：{}",Throwables.getStackTraceAsString(e));
            super.setError(e, result);
        }
        return result;
    }



}
