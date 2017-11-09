package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.Standards;
import com.ichinait.food.db.entity.StandardsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardsMapper {
    int countByExample(StandardsExample example);

    int deleteByExample(StandardsExample example);

    int deleteByPrimaryKey(String id);

    int insert(Standards record);

    int insertSelective(Standards record);

    List<Standards> selectByExampleWithBLOBs(StandardsExample example);

    List<Standards> selectByExample(StandardsExample example);

    Standards selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Standards record, @Param("example") StandardsExample example);

    int updateByExampleWithBLOBs(@Param("record") Standards record, @Param("example") StandardsExample example);

    int updateByExample(@Param("record") Standards record, @Param("example") StandardsExample example);

    int updateByPrimaryKeySelective(Standards record);

    int updateByPrimaryKeyWithBLOBs(Standards record);

    int updateByPrimaryKey(Standards record);
}