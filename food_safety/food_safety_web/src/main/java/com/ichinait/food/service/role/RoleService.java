package com.ichinait.food.service.role;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Role;
import com.ichinait.food.db.entity.RoleExample;
import com.ichinait.food.db.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class RoleService {
	
	@Resource
	private RoleMapper roleMapper;
	

	/**
	 * 通过roleName 查询role
	 * @param id
	 * @return
	 */
	public Role selectRoleByRoleId(String id){
		RoleExample example = new RoleExample();
		example.createCriteria().andIdEqualTo(id);
		List<Role> roles = roleMapper.selectByExample(example);
		Assert.notEmpty(roles, "根据=="+id+"==没找到对应的role！");
		return roles.get(0);
	}

	public Role selectRoleByName(String roleName){
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleNameEqualTo(roleName);
		List<Role> roles = roleMapper.selectByExample(example);
		Assert.notEmpty(roles, "根据==="+roleName+"===没找到对应的role！");
		return roles.get(0);
	}
	
	public List<Role> queryAllRole(){
		RoleExample example = new RoleExample();
		example.createCriteria().andStatusEqualTo(Constant.DATA_VALID_STATE);
		return roleMapper.selectByExample(example);
	}


	/**
	 * 根据角色名称模糊查询角色信息
	 * @param roleName
	 * @return
     */
	public PageInfo<Role> queryAllRoleByRoleName(int curror, String roleName){
		PageHelper.startPage(curror, Constant.PAGE_LIMIT);
		PageHelper.orderBy("create_time desc");

		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.DATA_VALID_STATE);
		if(!StringUtils.isEmpty(roleName)){
			criteria.andRoleNameLike("%" + roleName + "%");
		}
		List<Role> roleList = roleMapper.selectByExample(example);
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

		return pageInfo;
	}


	public boolean addRole(Role role) {
		String uid = UUID.randomUUID().toString();
		role.setId(uid);
		role.setStatus(Constant.DATA_VALID_STATE);
		role.setCreateTime(new Date());

		return roleMapper.insert(role)>0;
	}


	/**
	 * 校验角色
	 * @param roleName
	 * @return
     */
	public boolean selectValidateRoleByRoleName(String roleName) {
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleNameEqualTo(roleName);

		return roleMapper.selectByExample(example).size() > 0;
	}


	public boolean updateRoleByRoleId(Role role) {
		role.setUpdateTime(new Date());
		return roleMapper.updateByPrimaryKeySelective(role) > 0;
	}


	public boolean updateRole(Role role) {
		role.setUpdateTime(new Date());
		return roleMapper.updateByPrimaryKeySelective(role) > 0;
	}
}
