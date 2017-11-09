package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.Algorithm;
import com.ichinait.food.db.entity.AlgorithmExample;
import com.ichinait.food.db.entity.AlgorithmWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgorithmMapper {
    int countByExample(AlgorithmExample example);

    int deleteByExample(AlgorithmExample example);

    int deleteByPrimaryKey(String id);

    int insert(AlgorithmWithBLOBs record);

    int insertSelective(AlgorithmWithBLOBs record);

    List<AlgorithmWithBLOBs> selectByExampleWithBLOBs(AlgorithmExample example);

    List<Algorithm> selectByExample(AlgorithmExample example);

    AlgorithmWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AlgorithmWithBLOBs record, @Param("example") AlgorithmExample example);

    int updateByExampleWithBLOBs(@Param("record") AlgorithmWithBLOBs record, @Param("example") AlgorithmExample example);

    int updateByExample(@Param("record") Algorithm record, @Param("example") AlgorithmExample example);

    int updateByPrimaryKeySelective(AlgorithmWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AlgorithmWithBLOBs record);

    int updateByPrimaryKey(Algorithm record);
}