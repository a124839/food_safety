package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.SampleIndicator;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SampleIndicatorMapperPlus {
   void batchInsert(List<SampleIndicator> list);
}