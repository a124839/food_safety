package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.plus.ExcelProject;
import com.ichinait.food.db.entity.plus.ProjectPlus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ichinait on 2016/3/30.
 */
@Repository
public interface ProjectMapperPlus {
    List<ProjectPlus> selectProjectByCondition(Map<String,Object> params);

    ExcelProject selectProjectByIdForExcel(String id);
}
