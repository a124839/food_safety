package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.Sample;
import com.ichinait.food.db.entity.SampleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleMapper {
    int countByExample(SampleExample example);

    int deleteByExample(SampleExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sample record);

    int insertSelective(Sample record);

    List<Sample> selectByExample(SampleExample example);

    Sample selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sample record, @Param("example") SampleExample example);

    int updateByExample(@Param("record") Sample record, @Param("example") SampleExample example);

    int updateByPrimaryKeySelective(Sample record);

    int updateByPrimaryKey(Sample record);
}