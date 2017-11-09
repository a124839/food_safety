package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Algorithm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ichinait on 2016/3/30.
 */
@Repository
public interface AlgorithmMapperPlus {
    List<Algorithm> selectAlgorithmByCondition(Map<String, Object> params);

}
