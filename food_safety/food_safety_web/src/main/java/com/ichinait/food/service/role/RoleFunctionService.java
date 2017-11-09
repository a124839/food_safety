package com.ichinait.food.service.role;

import com.ichinait.food.db.entity.RoleExample;
import com.ichinait.food.db.entity.RoleFunctionMapping;
import com.ichinait.food.db.entity.RoleFunctionMappingExample;
import com.ichinait.food.db.entity.plus.RolePlus;
import com.ichinait.food.db.mapper.RoleFunctionMappingMapper;
import com.ichinait.food.db.mapper.plus.RoleFunctionMappingMapperPlus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class RoleFunctionService {
	
	@Resource
	private RoleFunctionMappingMapper roleFunctionMappingMapper;

	@Resource
	private RoleFunctionMappingMapperPlus roleFunctionMappingMapperPlus;


	public RolePlus selectRoleInfoByRoleId(String roleId) {
		RolePlus rolePlus = new RolePlus();
		List<String> authorities = roleFunctionMappingMapperPlus.selectFunctionsByRoleId(roleId);
		rolePlus.setId(roleId);
		rolePlus.setAuthorities(authorities);
		return rolePlus;
	}


	public boolean saveRoleAuthorization(RolePlus rolePlus) {
		RoleFunctionMappingExample example = new RoleFunctionMappingExample();
		example.createCriteria().andRoleIdEqualTo(rolePlus.getId());
		roleFunctionMappingMapper.deleteByExample(example);

		List<RoleFunctionMapping> list = new ArrayList<RoleFunctionMapping>();
		for(String str : rolePlus.getAuthorities()){
			RoleFunctionMapping roleFunction = new RoleFunctionMapping();
			roleFunction.setId(UUID.randomUUID().toString());
			roleFunction.setFunction(str);
			roleFunction.setRoleId(rolePlus.getId());
			roleFunction.setCt(new Date());
			roleFunction.setUt(new Date());
			list.add(roleFunction);
		}
		return roleFunctionMappingMapperPlus.insertRoleFunctions(list)>0;
	}
}
