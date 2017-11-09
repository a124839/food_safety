package com.ichinait.food.controller.instrument;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.db.entity.InstrumentWithBLOBs;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.dto.instrument.InstrumentQueryDTO;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.service.instrument.InstrumentService;
import com.ichinait.food.util.FileUtil;
import com.ichinait.food.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("instrument")
public class InstrumentController extends BaseController<Instrument,InstrumentService>{
	
private static Logger logger = LoggerFactory.getLogger(InstrumentController.class);
	
	@Autowired
	private InstrumentService instrumentService;

    @Autowired
    private AttachmentService attachmentService;
	
	@RequestMapping("toInstrumentPage")
    public String toSamplePage(){
        return "/instrument/instrument_list";
    }
    
    
    
    @RequestMapping("queryInstrument")
	@ResponseBody
	public Map<String,Object> querySample(@RequestParam(value="curr",defaultValue="1",required=true)int curror,InstrumentQueryDTO instrumentDTO){
    	logger.info("查询设备列表：{}",JsonMapper.nonEmptyMapper().toJson(instrumentDTO));
		Map<String,Object> result = Maps.newHashMap();
		PageInfo<Instrument> pageInfo = instrumentService.queryInstrument(curror, instrumentDTO);
		super.setSuccess(pageInfo, result);
		return result;
	}
    
    
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> deleteSample(String instrumentId){
    	Map<String,Object> result = Maps.newHashMap();
		instrumentService.deleteInstrument(instrumentId);
    	result.put("success",Constant.EXEC_SUCCESS);
    	return result;
    }


	@RequestMapping("toAddPage")
	public String toAddPage(){
		//TODO 设备分类从type表中查询
		return "/instrument/instrument_add";
	}

	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> saveInstrument(InstrumentWithBLOBs instrument,HttpServletRequest request){
		logger.debug("保存设备信息：{}",JsonMapper.nonEmptyMapper().toJson(instrument));
		Map<String,Object> result = Maps.newHashMap();
        try {
			MultipartRequest multipartRequest = (MultipartRequest) request;
			Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();
			MultipartFile file =  fileMap.get("attachment");
            User user = super.getSessionUser();
            if(file != null){
               String path = FileUtil.getInstrumentAttachmentUploadPath();
               //不分文件夹,所以url 和fileFullName 是一样的
               String attachmentId =  attachmentService.saveAttachment(file,user.getId(),path);
               instrument.setAttachmentId(attachmentId);
           }
            instrument.setOperator(user.getId());
            instrumentService.saveOrUpdateInstrument(instrument);
            result.put("success", Constant.EXEC_SUCCESS);
        } catch (Exception e) {
            logger.error("保存设备失败：{}", Throwables.getStackTraceAsString(e));
            result.put("success",Constant.EXEC_ERROR);
        }
		return result;
	}

	@RequestMapping("toEditPage")
	public String toInstrumentEditPage(String id, HttpServletRequest request){
		Instrument instrument = instrumentService.queryInstrumentById(id);
		request.setAttribute("instrument",instrument);
		return "/instrument/instrument_edit";
	}

    @RequestMapping("view")
    public String view(String id, HttpServletRequest request){
        Instrument instrument = instrumentService.queryInstrumentById(id);
        request.setAttribute("instrument",instrument);
        return "/instrument/instrument_view";
    }

}
