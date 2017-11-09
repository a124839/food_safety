package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.plus.InstrumentServiceRecordPlus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentServiceMapperPlus {
   List<InstrumentServiceRecordPlus> selectRecordByConditions(@Param("typeId") String typeId,@Param("instrumentId") String instrumentId);
   int updateServiceRecord(@Param("id") String id,@Param("status") byte status);
}