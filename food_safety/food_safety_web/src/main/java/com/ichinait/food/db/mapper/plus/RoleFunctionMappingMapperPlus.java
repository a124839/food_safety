package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Role;
import com.ichinait.food.db.entity.RoleFunctionMapping;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleFunctionMappingMapperPlus {
	
	List<String> selectFunctionsByUserId(String userId);

	List<String> selectFunctionsByRoleId(String roleId);

	int insertRoleFunctions(List<RoleFunctionMapping> list);
}
