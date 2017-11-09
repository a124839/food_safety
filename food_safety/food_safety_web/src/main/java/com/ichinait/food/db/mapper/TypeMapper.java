package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.Type;
import com.ichinait.food.db.entity.TypeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {
    int countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExampleWithBLOBs(TypeExample example);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExampleWithBLOBs(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKeyWithBLOBs(Type record);

    int updateByPrimaryKey(Type record);
}