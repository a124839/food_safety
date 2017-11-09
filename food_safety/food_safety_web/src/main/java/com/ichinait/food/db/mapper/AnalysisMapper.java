package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.Analysis;
import com.ichinait.food.db.entity.AnalysisExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisMapper {
    int countByExample(AnalysisExample example);

    int deleteByExample(AnalysisExample example);

    int deleteByPrimaryKey(String id);

    int insert(Analysis record);

    int insertSelective(Analysis record);

    List<Analysis> selectByExampleWithBLOBs(AnalysisExample example);

    List<Analysis> selectByExample(AnalysisExample example);

    Analysis selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Analysis record, @Param("example") AnalysisExample example);

    int updateByExampleWithBLOBs(@Param("record") Analysis record, @Param("example") AnalysisExample example);

    int updateByExample(@Param("record") Analysis record, @Param("example") AnalysisExample example);

    int updateByPrimaryKeySelective(Analysis record);

    int updateByPrimaryKeyWithBLOBs(Analysis record);

    int updateByPrimaryKey(Analysis record);
}