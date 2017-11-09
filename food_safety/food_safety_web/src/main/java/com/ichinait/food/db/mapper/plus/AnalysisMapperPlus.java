package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.plus.AnalysisPlus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface AnalysisMapperPlus {
	List<AnalysisPlus> selectAnalysisByConditions(Map<String, Object> params);

}
