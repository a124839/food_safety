package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.AlgorithmParams;
import com.ichinait.food.db.entity.AlgorithmParamsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgorithmParamsMapper {
    int countByExample(AlgorithmParamsExample example);

    int deleteByExample(AlgorithmParamsExample example);

    int deleteByPrimaryKey(String id);

    int insert(AlgorithmParams record);

    int insertSelective(AlgorithmParams record);

    List<AlgorithmParams> selectByExample(AlgorithmParamsExample example);

    AlgorithmParams selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AlgorithmParams record, @Param("example") AlgorithmParamsExample example);

    int updateByExample(@Param("record") AlgorithmParams record, @Param("example") AlgorithmParamsExample example);

    int updateByPrimaryKeySelective(AlgorithmParams record);

    int updateByPrimaryKey(AlgorithmParams record);
}