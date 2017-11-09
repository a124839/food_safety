package com.ichinait.food.controller.user;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Role;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.UserPlus;
import com.ichinait.food.dto.user.UserDTO;
import com.ichinait.food.service.role.RoleService;
import com.ichinait.food.service.user.UserService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController<User,UserService>{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	
	@RequestMapping(value="toUserManagePage",method=RequestMethod.GET)
	public String toUserManagePage(HttpServletRequest request){
	    List<Role> allRole = roleService.queryAllRole();
	    request.setAttribute("roles", allRole);
		return "/user/user_list";
	}
	
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryUserByConditions(
			@RequestParam(value="curr",defaultValue="1",required=true)int curror,
			@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="roleId",required=false)String roleId,
			@RequestParam(value="status",required=false)byte status
			){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			
			UserDTO conditions = new UserDTO();
			if(!StringUtils.isEmpty(userName)){
				conditions.setUserName(userName);
			}
			if(!StringUtils.isEmpty(roleId)){
				conditions.setRoleId(roleId);
				
			}
			conditions.setStatus(status);
			PageInfo<UserPlus> pageInfo = userService.queryUser(curror, conditions);
			super.setSuccess(pageInfo,result);
		} catch (Exception e) {
			logger.error("查询用户列表失败：{}",Throwables.getStackTraceAsString(e));
			super.setError(e,result);
		}
		return result;
	}
	
	/**
	 * 添加用户
	 * @param userDto
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addUser(UserDTO userDto){
		logger.info("=========添加用户:{}===============",JsonMapper.nonEmptyMapper().toJson(userDto));
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			boolean flag = userService.addUser(userDto);
			if(flag){
				result.put("success", Constant.EXEC_SUCCESS);
			}else{
				result.put("success", Constant.EXEC_FAILED);
			}
		} catch (Exception e) {
			logger.error("添加用户失败：{}",Throwables.getStackTraceAsString(e));
			super.setError(e, result);
		}
		
		return result;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteUser(@RequestParam(value="userId") String userId){
		logger.info("=========删除用户:{}===============",userId);
		Map<String,Object> result = new HashMap<String,Object>();
		User user = new User();
		user.setId(userId);
		user.setStatus(Constant.DATA_INVALID_STATE);
		try {
			boolean flag = userService.updateUser(user);
			if(flag){
				result.put("success", Constant.EXEC_SUCCESS);
			}else{
				result.put("success", Constant.EXEC_FAILED);
			}
		} catch (Exception e) {
			logger.error("删除用户失败：{}",Throwables.getStackTraceAsString(e));
			super.setError(e, result);
		}
		return result;
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateUser(UserDTO userDto){
		Map<String,Object> result = new HashMap<String,Object>();
		logger.info("=========修改用户信息:{}===============",JsonMapper.nonEmptyMapper().toJson(userDto));
		try {
			boolean flag = userService.updateUser(userDto);
			if(flag){
				result.put("success", Constant.EXEC_SUCCESS);
			}else{
				result.put("success", Constant.EXEC_FAILED);
			}
		} catch (Exception e) {
			logger.error("更新用户失败：{}",Throwables.getStackTraceAsString(e));
			super.setError(e, result);
		}
		return result;
	}
	
	
	/**
	 * 验证account是否可以注册
	 * @param loginAccount
	 * @return
	 */
	@RequestMapping(value="validateAccount",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryUserByLoginAccount(String loginAccount){
		Map<String,Object> result = new HashMap<String,Object>();
		logger.info("=========查询用户信息:{}===============",loginAccount);
		try {
			boolean flag = userService.queryUserByAccount(loginAccount);//true表示已被注册
			result.put("success", Constant.EXEC_SUCCESS);
			result.put("flag", !flag);
		} catch (Exception e) {
			logger.error("查询用户失败：{}",Throwables.getStackTraceAsString(e));
			super.setError(e, result);
		}
		return result;
	}

	@RequestMapping("verify")
	@ResponseBody
	public Map<String,Object> verify(String uid,byte status){
		Map<String,Object> result = Maps.newHashMap();
		boolean flag = userService.verify(uid,status);
		result.put("success",Constant.EXEC_SUCCESS);
		return result;
	}

}
