package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapperPlus {
	
	Role selectRoleByUserId(String userId);

}
