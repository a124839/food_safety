package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.dto.instrument.InstrumentQueryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ichinait on 2016/3/30.
 */
@Repository
public interface InstrumentMapperPlus {
    List<Instrument> selectInstrumentByConditions(InstrumentQueryDTO instrumentDto);
}
