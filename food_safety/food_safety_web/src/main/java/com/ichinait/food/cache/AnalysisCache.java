package com.ichinait.food.cache;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 分析数据缓存类，此类将分析数据的参数（算法，算法参数，数据ids）与分析结果缓存起来
 * 需要一个更新缓存策略(用一个thread)，定期移除已经过期的数据。
 * Created by ichinait on 2016/3/28.
 */
public class AnalysisCache {
    public static String PARAM_KEY = "params";
    public static String RESULT_KEY = "result";

    private static AnalysisCache instance;
    private Map<Long,Map<String,Object>> cache = Maps.newHashMap();

    private AnalysisCache(){

    }

    /**
     * 采用静态内部类的方式初始化缓存，防止并发问题
     * @return
     */
    public static AnalysisCache getInstance(){

        return SingletonHolder.analysisCache;
    }

    public Map<Long,Map<String,Object>> getCache(){
        return cache;
    }

    private static class SingletonHolder{
        private static AnalysisCache analysisCache = new AnalysisCache();
    }

}
