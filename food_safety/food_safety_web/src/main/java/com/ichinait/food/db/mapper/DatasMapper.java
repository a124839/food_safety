package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.entity.DatasExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatasMapper {
    int countByExample(DatasExample example);

    int deleteByExample(DatasExample example);

    int deleteByPrimaryKey(String id);

    int insert(Datas record);

    int insertSelective(Datas record);

    List<Datas> selectByExampleWithBLOBs(DatasExample example);

    List<Datas> selectByExample(DatasExample example);

    Datas selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Datas record, @Param("example") DatasExample example);

    int updateByExampleWithBLOBs(@Param("record") Datas record, @Param("example") DatasExample example);

    int updateByExample(@Param("record") Datas record, @Param("example") DatasExample example);

    int updateByPrimaryKeySelective(Datas record);

    int updateByPrimaryKeyWithBLOBs(Datas record);

    int updateByPrimaryKey(Datas record);
}