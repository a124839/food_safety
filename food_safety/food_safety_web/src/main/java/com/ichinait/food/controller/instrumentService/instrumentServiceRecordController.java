package com.ichinait.food.controller.instrumentService;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Type;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.InstrumentServiceRecordPlus;
import com.ichinait.food.service.instrument.InstrumentService;
import com.ichinait.food.service.instrumentService.InstrumentServiceRecordService;
import com.ichinait.food.service.type.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("instrumentServiceRecord")
public class instrumentServiceRecordController extends BaseController<com.ichinait.food.db.entity.InstrumentService,InstrumentService>{
	private static Logger logger = LoggerFactory.getLogger(InstrumentService.class);
	
	@Autowired
	private InstrumentServiceRecordService instrumentServiceRecordService;
	
	@Autowired
	private TypeService typeService;
	
	
	@RequestMapping("toInstrumnetServicePage")
	public String toInstrumnetServicePage(HttpServletRequest request){
        List<Type> types = typeService.queryTypeByParentId("0", 2);
        request.setAttribute("types",types);
        return "instrumentServiceRecord/instrument_service_record_list";
	}
	
	
	/**
	 * 通过服务类型查询列表
	 * @param curror
	 * @param typeId
	 * @return
	 */
	@RequestMapping("queryServiceRecord")
    @ResponseBody
   	public Map<String,Object> queryServiceRecord(@RequestParam(value="curr",defaultValue="1",required=true)int curror,String typeId,String instrumentId){
       	logger.info("查询服务记录列表：{}",typeId);
   		Map<String,Object> result = Maps.newHashMap();
   		PageInfo<InstrumentServiceRecordPlus> pageInfo = instrumentServiceRecordService.queryServiceRecordByCondition(curror, typeId,instrumentId);
   		super.setSuccess(pageInfo, result);
   		return result;
   	}
	

	
	
	
    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> deleteInstrumentServiceRecord(String id){
    	Map<String,Object> result = Maps.newHashMap();
    	instrumentServiceRecordService.deleteInstrumentServiceRecord(id);
    	result.put("success",Constant.EXEC_SUCCESS);
    	return result;
    }
    
    
    /**
     * 这里跳转页面 传服务类型
     * @param request
     * @return
     */
	@RequestMapping("toAddPage")
	public String toAddPage(HttpServletRequest request){
        List<Type> types = typeService.queryTypeByParentId("0", 2);
        request.setAttribute("types",types);
		return "/instrumentServiceRecord/service_record_add";
	}

    /*
    @RequestMapping("edit")
    public String toEditPage(String id, HttpServletRequest request){
    	InstrumentServiceRecordService sample = InstrumentServiceRecordService.queryInstrumentServiceByType(id);
        request.setAttribute("sample",sample);
        List<Type> types = InstrumentServiceRecordService.queryTypeByParentId("0", 1);
		request.setAttribute("lv1",types);
        return "/sample/sample_edit";
    }
	*/
    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> save(com.ichinait.food.db.entity.InstrumentService is){
        Map<String,Object> result = Maps.newHashMap();
        User user = super.getSessionUser();
        is.setOperator(user.getId());
        instrumentServiceRecordService.saveOrUpdate(is);
        result.put("success",Constant.EXEC_SUCCESS);
        return result;
    }


    @RequestMapping("toEditPage")
    public String toEditPage(HttpServletRequest request,String id){
        try {
            List<Type> types = typeService.queryTypeByParentId("0", 2);
            com.ichinait.food.db.entity.InstrumentService record = instrumentServiceRecordService.queryInstrumentServiceRecord(id);
            request.setAttribute("types",types);
            request.setAttribute("record",record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/instrumentServiceRecord/service_record_edit";
    }
	
	
}
