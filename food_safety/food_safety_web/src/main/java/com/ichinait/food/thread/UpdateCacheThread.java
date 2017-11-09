package com.ichinait.food.thread;

import com.ichinait.food.cache.AnalysisCache;
import com.ichinait.food.util.JsonMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by ichinait on 2016/3/28.
 */
public class UpdateCacheThread implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(UpdateCacheThread.class);

    @Override
    public void run() {
        Map<Long,Map<String,Object>> map = AnalysisCache.getInstance().getCache();
        Iterator<Long> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            Long key = iterator.next();
            if(isExpire(key)){
                logger.info("发现过期的缓存数据：{}", JsonMapper.nonEmptyMapper().toJson(map.get(key)));
                iterator.remove();
            }
        }
        logger.info("缓存清理完成...");
    }

    private boolean isExpire(long time){
        DateTime now = new DateTime();
        //一小时过期
        return (now.getMillis() - time)/1000>=3600;
    }
}
