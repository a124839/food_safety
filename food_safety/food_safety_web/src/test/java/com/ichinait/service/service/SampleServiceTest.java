package com.ichinait.service.service;

import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Sample;
import com.ichinait.food.db.entity.SampleExample;
import com.ichinait.food.db.mapper.SampleMapper;
import com.ichinait.food.service.sample.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class SampleServiceTest {
	@Autowired
	private SampleMapper sampleMapper;
	@Autowired
	private SampleService sampleService;

	@Test
	public void insertSample(){
		SampleExample example = new SampleExample();
		example.createCriteria().andStatusEqualTo(Constant.DATA_VALID_STATE);
		sampleMapper.deleteByExample(example);
		for(int i = 0;i<200;i++){
			Sample s = new Sample();
			s.setName("饺子粉"+i);
			s.setCode("000-"+i);
			s.setCt(new Date());
			s.setUt(new Date());
			s.setId(UUID.randomUUID().toString());
			s.setStatus((byte) 1);
			s.setMemo("测试");
			s.setProductionDate(new Date());
			s.setProducingArea("河北");
//			s.setScanningTime(new Date());
			s.setBatches("批次"+i);
			s.setManufactor("古船"+i);
			sampleMapper.insertSelective(s);
		}
	}

	@Test
	public void testHasRecord(){
		String typeId = "1";
		System.out.println(sampleService.hasRecord(typeId));
	}
}
