package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.SampleIndicator;
import com.ichinait.food.db.entity.SampleIndicatorExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleIndicatorMapper {
    int countByExample(SampleIndicatorExample example);

    int deleteByExample(SampleIndicatorExample example);

    int deleteByPrimaryKey(String id);

    int insert(SampleIndicator record);

    int insertSelective(SampleIndicator record);

    List<SampleIndicator> selectByExample(SampleIndicatorExample example);

    SampleIndicator selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SampleIndicator record, @Param("example") SampleIndicatorExample example);

    int updateByExample(@Param("record") SampleIndicator record, @Param("example") SampleIndicatorExample example);

    int updateByPrimaryKeySelective(SampleIndicator record);

    int updateByPrimaryKey(SampleIndicator record);
}