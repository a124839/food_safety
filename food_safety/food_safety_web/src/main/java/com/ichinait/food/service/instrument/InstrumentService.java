package com.ichinait.food.service.instrument;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.ichinait.food.cache.InstrumentCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.db.entity.InstrumentExample;
import com.ichinait.food.db.entity.InstrumentWithBLOBs;
import com.ichinait.food.db.mapper.InstrumentMapper;
import com.ichinait.food.db.mapper.plus.InstrumentMapperPlus;
import com.ichinait.food.dto.instrument.InstrumentQueryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class InstrumentService {
	
	@Resource
	private InstrumentMapper instrumentMapper;
	@Resource
	private InstrumentMapperPlus instrumentMapperPlus;
	
	public PageInfo<Instrument> queryInstrument(int curror,InstrumentQueryDTO instrumentDto){
		PageHelper.startPage(curror, Constant.PAGE_LIMIT);
		PageHelper.orderBy("i.ct desc");
		List<Instrument> instruments = instrumentMapperPlus.selectInstrumentByConditions(instrumentDto);
		PageInfo<Instrument> pageInfo = new PageInfo<Instrument>(instruments);
		return pageInfo;
	}


	public List<Instrument> queryInstrumentNoPage(){
		InstrumentExample example = new InstrumentExample();
		example.createCriteria().andStatusEqualTo(Constant.DATA_VALID_STATE).andCategoryEqualTo(Constant.INSTRUMENT_CATEGORY);
		List<Instrument> instruments = instrumentMapper.selectByExample(example);
		return instruments;
	}

	public boolean deleteInstrument(String id){
		InstrumentWithBLOBs instrument = new InstrumentWithBLOBs();
		instrument.setId(id);
		instrument.setStatus(Constant.DATA_INVALID_STATE);
		InstrumentCache.getInstance().remove(instrument);
		return instrumentMapper.updateByPrimaryKeySelective(instrument)>0;
	}

	public boolean saveOrUpdateInstrument(InstrumentWithBLOBs instrument) throws  Exception{
		boolean flag = false;
		instrument.setUt(new Date());
		if(Strings.isNullOrEmpty(instrument.getId())){
			instrument.setStatus(Constant.DATA_VALID_STATE);
			instrument.setId(UUID.randomUUID().toString());
			instrument.setCt(new Date());
			flag =  instrumentMapper.insert(instrument)>0;
			if(instrument.getCategory().equals(Constant.INSTRUMENT_CATEGORY)){
				InstrumentCache.getInstance().add(instrument);
			}
		}else{
			flag = instrumentMapper.updateByPrimaryKeySelective(instrument)>0;
			InstrumentCache.getInstance().update();
		}
		return flag;
	}


	public Instrument queryInstrumentById(String id){
		return  instrumentMapper.selectByPrimaryKey(id);
	}

}
