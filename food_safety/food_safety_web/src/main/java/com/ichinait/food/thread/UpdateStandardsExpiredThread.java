package com.ichinait.food.thread;

import com.ichinait.food.service.standards.StandardsService;
import com.ichinait.food.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiao 2016-04-12
 */
public class UpdateStandardsExpiredThread implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(UpdateStandardsExpiredThread.class);

    @Override
    public void run() {
        logger.info("标准文件过期处理");
        StandardsService standardsService = (StandardsService) SpringContextHolder.getBean("standardsService");
        standardsService.updateStandardsExpiredThread();
    }


}
