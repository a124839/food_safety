package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.entity.plus.DataPlus;
import com.ichinait.food.db.entity.plus.ExcelDatas;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface DataMapperPlus {
	List<DataPlus> selectDataByConditions(Map<String,Object> params);
	
	List<DataPlus> selectDatasPlusByIds(List<String> ids);

	List<Datas> selectDatasByIds(List<String> ids);

	List<ExcelDatas> selectExcelDatasByProjectId(String projectId);
}
