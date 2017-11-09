package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Standards;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ichinait on 16-4-6.
 */
@Repository
public interface StandardsMapperPlus {
    List<Standards> selectStandardsByConditions(Standards standards);

    /**
     * CreateBy xiao 2016-04-12
     * 定时清理已过期的标准文件
     */
    void updateStandardsExpiredThread();
}
