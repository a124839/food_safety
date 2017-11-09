package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.InstrumentService;
import com.ichinait.food.db.entity.InstrumentServiceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentServiceMapper {
    int countByExample(InstrumentServiceExample example);

    int deleteByExample(InstrumentServiceExample example);

    int deleteByPrimaryKey(String id);

    int insert(InstrumentService record);

    int insertSelective(InstrumentService record);

    List<InstrumentService> selectByExample(InstrumentServiceExample example);

    InstrumentService selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InstrumentService record, @Param("example") InstrumentServiceExample example);

    int updateByExample(@Param("record") InstrumentService record, @Param("example") InstrumentServiceExample example);

    int updateByPrimaryKeySelective(InstrumentService record);

    int updateByPrimaryKey(InstrumentService record);
}