package com.ichinait.food.db.mapper.plus;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ichinait.food.db.entity.Sample;
import com.ichinait.food.db.entity.plus.SamplePlus;

@Repository
public interface SampleMapperPlus {
	List<Sample> selectSampleByConditions(Map<String,Object> params);
	SamplePlus selectSampleById(String id);
}
