package com.ichinait.food.queue;

import com.google.common.collect.Maps;
import com.ichinait.food.cache.AnalysisCache;
import com.ichinait.food.dto.analysis.AnalysisDTO;
import com.ichinait.food.dto.analysis.AnalysisDatasDTO;
import com.ichinait.food.service.datas.DatasService;
import com.ichinait.food.util.JsonMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static com.ichinait.food.util.SpringContextHolder.getBean;

/**
 * Created by IChinait on 2016/6/22.
 * 调用算法的线程
 */
public class CalculateWorker extends Worker<AnalysisDTO>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateWorker.class);

    public CalculateWorker(BlockingQueue queue, CountDownLatch countDownLatch){
            super(queue,countDownLatch);
    }


    @Override
    public void doWork(AnalysisDTO o) {
        LOGGER.debug("异步分析数据：{}", JsonMapper.nonEmptyMapper().toJson(o));



        DatasService datasService = (DatasService) getBean("datasService");
        try {
            List<AnalysisDatasDTO> analysisDatasDTOs = datasService.handlerDatas(o,o.getDataIds());
            //将分析参数与结果存放到内存中,当前时间毫秒数为key
            Long key = new DateTime().getMillis();
            Map<String, Object> analysisInfo = getAnalysisInfoMap(o, analysisDatasDTOs);
            AnalysisCache.getInstance().getCache().put(key, analysisInfo);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            WebUtils.setSessionAttribute(request,"cacheKey",key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getAnalysisInfoMap(AnalysisDTO analysisDTO, List<AnalysisDatasDTO> analysisDatasDTOs) {
        Map<String, Object> analysisInfo = Maps.newHashMap();
        //分析参数
        analysisInfo.put(AnalysisCache.PARAM_KEY, analysisDTO);
        //分析结果
        analysisInfo.put(AnalysisCache.RESULT_KEY, analysisDatasDTOs);
        return analysisInfo;
    }
}
