package com.ichinait.food.queue;

import com.google.common.base.Throwables;
import com.ichinait.food.dto.analysis.AnalysisDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by IChinait on 2016/6/22.
 * 调用算法的线程池
 */
public class Calculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    private int workThread = 10;
    private final BlockingQueue<AnalysisDTO> queue = new LinkedBlockingQueue();
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(workThread,15,200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(100));

    private Calculator(){

    }


    /**
     * 将线程放入线程池
     */
    public void start(){
        for(int i=0;i<workThread;i++){
            executor.execute(new CalculateWorker(queue,countDownLatch));
        }
        LOGGER.debug("线程全部启动...");
    }

    /**
     * 放闸
     */
    public void countDown(){
        countDownLatch.countDown();

    }

    public void put(AnalysisDTO o){
        try {
            queue.put(o);
        } catch (InterruptedException e) {
            LOGGER.error("处理数据，放入队列出错：{}", Throwables.getStackTraceAsString(e));
        }
    }

    private static class SingletonHolder{
        private static Calculator calculator = new Calculator();
    }

    public static Calculator getInstance(){
        return SingletonHolder.calculator;
    }



}
