package com.ichinait.service.service;

import com.google.common.collect.Maps;
import com.ichinait.food.db.entity.Type;
import com.ichinait.food.db.entity.plus.TypePlus;
import com.ichinait.food.db.mapper.plus.TypeMapperPlus;
import com.ichinait.food.util.JsonMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/spring/applicationContext.xml")
public class TypeService {
	
	@Autowired
	private TypeMapperPlus typeMapperPlus;
	
	@Test
	public void select(){
		Map<String,Object> params = Maps.newHashMap();
		params.put("categoryLv1Id", "1");
		List<TypePlus> list = typeMapperPlus.selectTypeList(params);
		System.out.println(JsonMapper.nonEmptyMapper().toJson(list));
	}

	public static void main(String[] args) {
		Type type = new Type();
		String str = "{\"id\":\"123\",\"name\":\"123123\"}";
//		type.setId("123");
//		type.setName("123123");
		Type t = JsonMapper.nonEmptyMapper().fromJson(str,Type.class);
		System.out.println(t.getName());
	}
}
