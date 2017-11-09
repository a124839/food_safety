package com.ichinait.food.cache;

import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.service.instrument.InstrumentService;
import com.ichinait.food.util.SpringContextHolder;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 设备缓存类
 * Created by ichinait on 2016/3/15.
 */
public class InstrumentCache {
    private  static InstrumentCache instance;

    private List<Instrument> cachedInstruments;

    /**
     * 更新缓存
     */
    public void update(){
        InstrumentService instrumentService = (InstrumentService) SpringContextHolder.getBean("instrumentService");
        cachedInstruments = instrumentService.queryInstrumentNoPage();
    }

    public void add(Instrument instrument){
        this.cachedInstruments.add(instrument);
    }

    public void remove(Instrument instrument){
        this.cachedInstruments.remove(instrument);
    }

    public List<Instrument> getCachedInstruments() {
        if(CollectionUtils.isEmpty(cachedInstruments)){
           update();
        }
        return cachedInstruments;
    }

    private InstrumentCache(){

    }

    public static InstrumentCache getInstance(){
        return SingletonHolder.instrumentCache;
    }

    private static class SingletonHolder{
        private static InstrumentCache  instrumentCache = new InstrumentCache();
    }
}
