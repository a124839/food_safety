package com.ichinait.service.service;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ichinait.food.db.entity.Role;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.mapper.RoleMapper;
import com.ichinait.food.db.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class UserService {
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private UserMapper userMapper;
	
	
	@Test
	public void insertRole(){
		Role r = new Role();
		r.setId(UUID.randomUUID().toString());
		r.setCreateTime(new Date());
		r.setUpdateTime(new Date());
//		r.setRoleName("检测员");
//		r.setRoleName("质量管理员");
		r.setRoleName("管理员");
		r.setShiroName("admin");
		roleMapper.insert(r);
	}
	
	@Test
	public void insertUser(){
		User record = new User();
		record.setCt(new Date());
		record.setUt(new Date());
		record.setId(UUID.randomUUID().toString());
		record.setLoginAccount("tom");
		record.setPassword("1");
		userMapper.insert(record);
		record.setId(UUID.randomUUID().toString());
		record.setLoginAccount("jerry");
		record.setId(UUID.randomUUID().toString());
		userMapper.insert(record);
		record.setLoginAccount("lufy");
		record.setId(UUID.randomUUID().toString());
		userMapper.insert(record);
		record.setLoginAccount("brook");
		record.setId(UUID.randomUUID().toString());
		userMapper.insert(record);
	}

}
