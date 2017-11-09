package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.RoleFunctionMapping;
import com.ichinait.food.db.entity.RoleFunctionMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleFunctionMappingMapper {
    int countByExample(RoleFunctionMappingExample example);

    int deleteByExample(RoleFunctionMappingExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoleFunctionMapping record);

    int insertSelective(RoleFunctionMapping record);

    List<RoleFunctionMapping> selectByExample(RoleFunctionMappingExample example);

    RoleFunctionMapping selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleFunctionMapping record, @Param("example") RoleFunctionMappingExample example);

    int updateByExample(@Param("record") RoleFunctionMapping record, @Param("example") RoleFunctionMappingExample example);

    int updateByPrimaryKeySelective(RoleFunctionMapping record);

    int updateByPrimaryKey(RoleFunctionMapping record);
}