package com.ichinait.service.service;

import com.ichinait.food.service.instrumentService.InstrumentServiceRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
public class InstrumentServiceRecordTest {
	@Autowired
	private InstrumentServiceRecordService instrumentServiceRecordService;

	@Test
	public void query(){
//		PageInfo<InstrumentService> pageInfo = instrumentServiceRecordService.queryServiceRecordByCondition(1, "1");
//		List<InstrumentService> list = pageInfo.getList();
//		System.out.println("====================="+list.size());
		}
}
