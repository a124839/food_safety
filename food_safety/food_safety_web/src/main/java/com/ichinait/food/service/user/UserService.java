package com.ichinait.food.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.*;
import com.ichinait.food.db.entity.plus.UserPlus;
import com.ichinait.food.db.mapper.UserMapper;
import com.ichinait.food.db.mapper.UserRoleMappingMapper;
import com.ichinait.food.db.mapper.plus.UserMapperPlus;
import com.ichinait.food.dto.user.UserDTO;
import com.ichinait.food.service.role.RoleService;
import com.ichinait.food.util.DigestUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class UserService {
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserMapperPlus userMapperPlus;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRoleMappingMapper userRoleMappingMapper;
	
	
	
	/**
	 * 登录系统
	 * @param account
	 * @param password
	 * @return
	 * @date:2016年1月15日
	 * @author:zhangbo@ichinait.com
	 */
	public User login(String account,String password) throws Exception{
		UserExample example = new UserExample();
		example.createCriteria().andLoginAccountEqualTo(account).andPasswordEqualTo(password).andStatusEqualTo(Constant.DATA_VALID_STATE);
		List<User> list = userMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	
	/**
	 * 分页查询数据
	 * @param curror 当前页
	 * @param conditions 查询条件
	 * @return
	 */
	public PageInfo<UserPlus> queryUser(int curror,UserDTO conditions) throws Exception{
		PageHelper.startPage(curror, Constant.PAGE_LIMIT);
		PageHelper.orderBy("u.ct desc");

		List<UserPlus> users =  userMapperPlus.selectUserListByConditions(conditions);
		PageInfo<UserPlus> pageInfo = new PageInfo<UserPlus>(users);
		return pageInfo;
	}
	
	/**
	 * 
	 * Description:添加用户
	 * @param userDto
	 * @return
	 * @author:zhangbo@ichinait.com
	 * @version 1.0
	 * Create at: 2016年1月15日 下午2:06:56
	 */
	public boolean addUser(UserDTO userDto) throws Exception{
		User user = copy(userDto);
		String uid = UUID.randomUUID().toString();
		user.setId(uid);
		user.setCt(new Date());
//		user.setPassword(DigestUtil.md5(userDto.getPassword()));
		user.setStatus(Constant.DATA_VALID_STATE);
		//添加t_user_role_mapping 表记录
		UserRoleMapping urm = new UserRoleMapping();
		urm.setCt(new Date());
		urm.setUt(new Date());
		urm.setId(UUID.randomUUID().toString());
		Role role = roleService.selectRoleByRoleId(userDto.getRoleId());
		if(role != null){//万一没查出来呢
			urm.setRoleId(role.getId());
			urm.setUserId(uid);
			userRoleMappingMapper.insert(urm);
		}
		
		return userMapper.insert(user)>0;
	}
	
	/**
	 * @description:修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 * @author:zhangbo@ichinait.com
	 * @version 1.0
	 * Create at: 2016年1月15日 下午2:08:36
	 */
	public boolean updateUser(User user) throws Exception{
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}
	
	public boolean updateUser(UserDTO userDto) throws Exception{
		User user = copy(userDto);
		user.setStatus(Constant.DATA_VALID_STATE);
		//更新权限,修改用户信息使用频率较低，这里就不判断是否需要更新了，直接每次都更新
		Role role = roleService.selectRoleByRoleId(userDto.getRoleId());
		if(role != null){//万一没查出来呢
			UserRoleMapping urm = new UserRoleMapping();
			urm.setRoleId(role.getId());
			urm.setUt(new Date());
			UserRoleMappingExample example = new UserRoleMappingExample();
			example.createCriteria().andUserIdEqualTo(userDto.getId());
			int updateFlag = userRoleMappingMapper.updateByExampleSelective(urm, example);
			if(updateFlag == 0){//如果这个用户没有权限，需要新增一条，虽然不太可能。以防万一
				urm.setId(UUID.randomUUID().toString());
				urm.setCt(new Date());
				urm.setUserId(userDto.getId());
				userRoleMappingMapper.insert(urm);
			}
		}
		return this.updateUser(user);
	}
	
	/**
	 * 提个方法出来，增加更新都要用
	 * @param dto
	 * @return
	 */
	private User copy(UserDTO dto){
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		user.setUt(new Date());
		if(!Strings.isNullOrEmpty(user.getPassword()) ){
			user.setPassword(DigestUtil.md5(user.getPassword()));
		}
		return user;
	}
	
	/**
	 * 
	 * @description: 查询账户是否重复
	 * @param account
	 * @return
	 * @author:zhangbo@ichinait.com
	 * @version 1.0
	 * @date 2016年2月18日
	 */
	public boolean queryUserByAccount(String account){
		UserExample example = new UserExample();
        List<Byte> status = Lists.newArrayList();
        status.add(Constant.USER_STATUS_NORMAL);
        status.add(Constant.USER_STATUS_UNVERIFIED);
        example.createCriteria().andLoginAccountEqualTo(account).andStatusIn(status);
		return userMapper.countByExample(example)>0;
	}


	public boolean verify(String uid,byte status){
		UserExample example = new UserExample();
		User user = new User();
		user.setId(uid);
		user.setStatus(status);
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	public boolean reg(UserDTO userDTO){
		User user = copy(userDTO);
		String uid = UUID.randomUUID().toString();
		user.setId(uid);
		user.setCt(new Date());
		user.setStatus(Constant.USER_STATUS_UNVERIFIED);
		//添加t_user_role_mapping 表记录
		UserRoleMapping urm = new UserRoleMapping();
		urm.setCt(new Date());
		urm.setUt(new Date());
		urm.setId(UUID.randomUUID().toString());
		Role role = roleService.selectRoleByName(userDTO.getRoleId().equals("1")?"学生":"导师");
		if(role != null){//万一没查出来呢
			urm.setRoleId(role.getId());
			urm.setUserId(uid);
			userRoleMappingMapper.insert(urm);
		}
		return userMapper.insert(user)>0;
	}

}
