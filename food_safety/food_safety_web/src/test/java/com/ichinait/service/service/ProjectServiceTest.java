package com.ichinait.service.service;

import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Algorithm;
import com.ichinait.food.db.entity.Project;
import com.ichinait.food.db.mapper.AlgorithmMapper;
import com.ichinait.food.db.mapper.ProjectMapper;
import com.ichinait.food.util.SpringContextHolder;

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
public class ProjectServiceTest {
    @Autowired
    private ProjectMapper projectMapper;


    @Test
    public void insert(){
        for (int i = 0;i<10;i++){
            Project p = new Project();
            p.setId(UUID.randomUUID().toString());
            p.setCt(new Date());
            p.setUt(new Date());
            p.setName("实验"+i);
            p.setStartDate(new Date());
            p.setStatus(Constant.DATA_VALID_STATE);
            p.setEndDate(new Date());
            projectMapper.insert(p);
        }
    }
    
    @Test
    public void select(){
    	ProjectMapper mapper = (ProjectMapper) SpringContextHolder.getBean("projectMapper");
    	mapper.selectByPrimaryKey("123");
    }

}
