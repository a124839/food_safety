package com.ichinait.food.db.mapper;

import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.db.entity.InstrumentExample;
import com.ichinait.food.db.entity.InstrumentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InstrumentMapper {
    int countByExample(InstrumentExample example);

    int deleteByExample(InstrumentExample example);

    int deleteByPrimaryKey(String id);

    int insert(InstrumentWithBLOBs record);

    int insertSelective(InstrumentWithBLOBs record);

    List<InstrumentWithBLOBs> selectByExampleWithBLOBs(InstrumentExample example);

    List<Instrument> selectByExample(InstrumentExample example);

    InstrumentWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InstrumentWithBLOBs record, @Param("example") InstrumentExample example);

    int updateByExampleWithBLOBs(@Param("record") InstrumentWithBLOBs record, @Param("example") InstrumentExample example);

    int updateByExample(@Param("record") Instrument record, @Param("example") InstrumentExample example);

    int updateByPrimaryKeySelective(InstrumentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InstrumentWithBLOBs record);

    int updateByPrimaryKey(Instrument record);
}