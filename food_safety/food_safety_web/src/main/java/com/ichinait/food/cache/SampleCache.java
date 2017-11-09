package com.ichinait.food.cache;

import com.ichinait.food.db.entity.Sample;
import com.ichinait.food.service.sample.SampleService;
import com.ichinait.food.util.SpringContextHolder;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 样品缓存类
 * Created by ichinait on 2016/3/15.
 */
public class SampleCache {

    private List<Sample> cachedSamples;

    /**
     * 更新缓存
     */
    public void update(){
        SampleService sampleService = (SampleService) SpringContextHolder.getBean("sampleService");
        cachedSamples = sampleService.querySamplesNoPage();
    }

    public void add(Sample sample){
        this.cachedSamples.add(sample);
    }

    public void remove(Sample sample){
        this.cachedSamples.remove(sample);
    }

    public List<Sample> getCachedSamples() {
        if(CollectionUtils.isEmpty(cachedSamples)){
           update();
        }
        return cachedSamples;
    }

    private SampleCache(){

    }

    public static SampleCache getInstance(){
       return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        private static SampleCache instance = new SampleCache();
    }
}
