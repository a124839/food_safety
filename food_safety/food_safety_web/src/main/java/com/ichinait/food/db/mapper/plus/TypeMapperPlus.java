package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.plus.TypePlus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import  com.ichinait.food.db.entity.Type;
import java.util.List;
import java.util.Map;
@Repository
public interface TypeMapperPlus {
	
	List<TypePlus> selectTypeList(Map<String,Object> params);
	TypePlus selectTypeById(String id);

	List<Type> selectServiceType(@Param("id") String id);
	
	List<Type> selectFirstTypeById(String id);

	List<Type> selectFirstTypeByConditions(@Param("id")String id);
}
