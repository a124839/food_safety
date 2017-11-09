package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.UserRoleMapping;
import com.ichinait.food.db.entity.UserRoleMappingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMappingMapper {
    int countByExample(UserRoleMappingExample example);

    int deleteByExample(UserRoleMappingExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserRoleMapping record);

    int insertSelective(UserRoleMapping record);

    List<UserRoleMapping> selectByExample(UserRoleMappingExample example);

    UserRoleMapping selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserRoleMapping record, @Param("example") UserRoleMappingExample example);

    int updateByExample(@Param("record") UserRoleMapping record, @Param("example") UserRoleMappingExample example);

    int updateByPrimaryKeySelective(UserRoleMapping record);

    int updateByPrimaryKey(UserRoleMapping record);
}