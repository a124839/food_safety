package com.ichinait.service.service;

import com.ichinait.food.cache.InstrumentCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.db.entity.InstrumentExample;
import com.ichinait.food.db.entity.InstrumentWithBLOBs;
import com.ichinait.food.db.entity.Project;
import com.ichinait.food.db.mapper.InstrumentMapper;
import com.ichinait.food.db.mapper.ProjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ichinait on 2016/3/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class InstrumentServiceTest {
    @Autowired
    private InstrumentMapper instrumentMapper;


    @Test
    public void insert(){
        InstrumentExample example = new InstrumentExample();
        example.createCriteria().andStatusEqualTo(Constant.DATA_VALID_STATE);
        instrumentMapper.deleteByExample(example);
        for (int i = 0;i<17;i++){
            InstrumentWithBLOBs instrument = new InstrumentWithBLOBs();
            instrument.setId(UUID.randomUUID().toString());
            instrument.setCt(new Date());
            instrument.setStatus(Constant.DATA_VALID_STATE);
            instrument.setName("光谱仪"+i);
            instrument.setCode("GP00"+i);
            instrument.setManufactor("xx分析仪制造厂"+i);
            instrument.setModel("v70"+i);
            instrument.setType("光谱分析");
            instrument.setMemo("测试");
            instrument.setProductionDate(new Date());
            instrument.setPurchaseDate(new Date());
            instrumentMapper.insert(instrument);
        }
    }

    @Test
    public void updateCache(){
        InstrumentCache.getInstance().update();
    }

    @Test
    public void testEquals(){
        Instrument instrument = new Instrument();
        instrument.setId("1");
        instrument.setCode("2");
        Instrument instrument1 = new Instrument();
        instrument1.setId("2");
        instrument1.setCode("1");
        System.out.println(instrument.equals(instrument1));
    }

}
