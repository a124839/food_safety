package com.ichinait.service.service;

import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Algorithm;
import com.ichinait.food.db.entity.AlgorithmWithBLOBs;
import com.ichinait.food.db.mapper.AlgorithmMapper;
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
public class AlgorithmServiceTest {
    @Autowired
    private AlgorithmMapper algorithmMapper;


    @Test
    public void insert(){
        for (int i = 0;i<50;i++){
            AlgorithmWithBLOBs algorithm = new AlgorithmWithBLOBs();
            algorithm.setId(UUID.randomUUID().toString());
            algorithm.setName("算法"+i);
            algorithm.setStatus(Constant.DATA_VALID_STATE);
            algorithm.setType("1");
            algorithm.setUt(new Date());
            algorithm.setCt(new Date());
            algorithmMapper.insert(algorithm);
        }
    }

}
