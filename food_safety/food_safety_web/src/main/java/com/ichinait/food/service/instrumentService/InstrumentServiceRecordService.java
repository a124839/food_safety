package com.ichinait.food.service.instrumentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.InstrumentService;
import com.ichinait.food.db.entity.plus.InstrumentServiceRecordPlus;
import com.ichinait.food.db.mapper.InstrumentServiceMapper;
import com.ichinait.food.db.mapper.plus.InstrumentServiceMapperPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)//
public class InstrumentServiceRecordService {
	@Autowired
	private InstrumentServiceMapper instrumentServiceMapper;//新建对象
	@Autowired
	private InstrumentServiceMapperPlus instrumentServiceMapperplus;

	/**
	 * 根据服务类型查询记录
	 * @param curr
	 * @param typeId
     * @return
     */
	
	public PageInfo<InstrumentServiceRecordPlus> queryServiceRecordByCondition(int curr,String typeId,String instrumentId){
		PageHelper.startPage(curr, Constant.PAGE_LIMIT);//curr表示分页，后面的参数表示分页后的页数
	    PageHelper.orderBy("s.ct desc");//查询出的列表降序排列
		List<InstrumentServiceRecordPlus> list = instrumentServiceMapperplus.selectRecordByConditions(typeId,instrumentId);//获取列表
		PageInfo<InstrumentServiceRecordPlus> pageInfo = new PageInfo<InstrumentServiceRecordPlus>(list);//用来返回总页数
		return pageInfo;
	}
	
	//删除
	  public boolean deleteInstrumentServiceRecord(String id){
		  return instrumentServiceMapperplus.updateServiceRecord(id,Constant.DATA_INVALID_STATE)>0;
	}
	 

	//更新
	  public boolean saveOrUpdate(InstrumentService instrumentService){
		  boolean flag = false;
		  instrumentService.setUt(new Date());
		  if(Strings.isNullOrEmpty(instrumentService.getId())){
			  String id = UUID.randomUUID().toString();
	            instrumentService.setCt(new Date());
	            instrumentService.setStatus(Constant.DATA_VALID_STATE);
	            instrumentService.setId(id);
	            flag = instrumentServiceMapper.insert(instrumentService)>0;
		  }else{
			  flag = instrumentServiceMapper.updateByPrimaryKeySelective(instrumentService)>0;
		  }
		return flag;  		  
	  }

	public InstrumentService queryInstrumentServiceRecord(String id){
		return instrumentServiceMapper.selectByPrimaryKey(id);
	}
	
}
