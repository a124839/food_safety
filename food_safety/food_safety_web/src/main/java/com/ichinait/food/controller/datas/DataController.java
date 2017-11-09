package com.ichinait.food.controller.datas;
import com.fasterxml.jackson.databind.JavaType;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ichinait.food.cache.InstrumentCache;
import com.ichinait.food.cache.ProjectCache;
import com.ichinait.food.cache.SampleCache;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Attachment;
import com.ichinait.food.db.entity.Datas;
import com.ichinait.food.db.entity.Instrument;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.DataPlus;
import com.ichinait.food.dto.data.DataDTO;
import com.ichinait.food.dto.data.ValuesDTO;
import com.ichinait.food.exception.ErrorMessage;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.service.datas.DatasService;
import com.ichinait.food.service.instrument.InstrumentService;
import com.ichinait.food.util.FileUtil;
import com.ichinait.food.util.JsonMapper;
import com.ichinait.food.util.PropertiesLoader;
import com.ichinait.food.util.ZipUtil;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("data")
public class DataController extends BaseController<Datas,DatasService>{
	private static Logger logger = LoggerFactory.getLogger(DataController.class);
	
	@Resource
	private DatasService datasService;
	@Resource
	private AttachmentService attachmentService;
	@Resource
	private InstrumentService instrumentService;



	@RequestMapping(value="toDataManagePage",method=RequestMethod.GET)
	public String toDataManagePage(HttpServletRequest request){
		request.setAttribute("samples", SampleCache.getInstance().getCachedSamples());
		request.setAttribute("projects", ProjectCache.getInstance().getCachedProjects());
		request.setAttribute("instruments", InstrumentCache.getInstance().getCachedInstruments());
		return "/data/data_list";
	}
	
	
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryDatasByConditions(
			@RequestParam(value="curr",defaultValue="1",required=true)int curror,
			@RequestParam(value="pageSize",defaultValue = "15",required = false)int pageSize,
			DataDTO dto){
		logger.info("查询数据：{}",JsonMapper.nonEmptyMapper().toJson(dto));
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			PageInfo<DataPlus> pageInfo = datasService.queryDataByConditions(curror,pageSize,dto);
			result.put("recordsTotal", pageInfo.getTotal());//总记录数
			result.put("totalPages", pageInfo.getPages());//总页数
			result.put("list", pageInfo.getList());
			result.put("success", Constant.EXEC_SUCCESS);
		} catch (Exception e) {
			logger.error("查询数据列表失败：{}",Throwables.getStackTraceAsString(e));
			result.put("errorCode", ErrorMessage.SYSTEM_ERROR.getCode());
			result.put("errorMessage", ErrorMessage.SYSTEM_ERROR.getMsg());
			result.put("success", Constant.EXEC_FAILED);
		}
		return result;
	}
	
	
	/**
	 * 上传文件
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("upload")
	@ResponseBody
	public Map<String,Object> upload(HttpServletRequest request,
			@RequestParam("project") String projectId,
			@RequestParam("instrument")String instrumentId,
									 @RequestParam(name="sample",required=false) String sampleId,
									 String resolution,
									 String wavelengthRange,
									 int scanningTimes,
									 String scanningDuration
			) {
		Map<String,Object> result = new HashMap<String,Object>();
		logger.info("上传文件:仪器：{},项目：{},样品：{},分辨率：{},波长范围：{},扫描时间：{},扫描次数：{}",instrumentId,projectId,sampleId,resolution,wavelengthRange,scanningDuration,scanningTimes);
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = req.getFileNames();
		while(fileNames.hasNext()){
		    String name = fileNames.next();
		    List<MultipartFile> files = req.getFiles(name);
		    try {
                List<String> errorFiles = datasService.saveFile(files,user.getId(),projectId,instrumentId,sampleId,wavelengthRange,resolution,scanningTimes,scanningDuration);
                result.put("success", Constant.EXEC_SUCCESS);
                result.put("errorFiles", errorFiles);
            } catch (IOException e) {
                result.put("success", Constant.EXEC_FAILED);
                logger.error("上传文件出错："+Throwables.getStackTraceAsString(e));
            }
		}
		return result;
	}

	@RequestMapping("del")
	@ResponseBody
	public Map<String,Object> deleteDatas(String id){
		logger.info("删除数据：{}",id);
		Map<String,Object> result = Maps.newHashMap();
		boolean delSuccess = datasService.deleteDatas(id);
		if(delSuccess){
			result.put("success",Constant.EXEC_SUCCESS);
		}else{
			result.put("success",Constant.EXEC_FAILED);
		}
		return result;
	}

	@RequestMapping("toDatasViewPage")
	public  String toDatasViewPage(HttpServletRequest request){
		return "data/data_view";
	}

	@RequestMapping("showDatas")
	@ResponseBody
	public  Map<String,Object> showDatas(String id,HttpServletRequest request){
		logger.info("查询数据详情：{}",id);
		Map<String,Object> result = Maps.newHashMap();
		Datas datas = datasService.queryDatasDetail(id);
		if(datas != null){
		    Instrument instrument = instrumentService.queryInstrumentById(datas.getInstrumentId());
			JavaType type = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,ValuesDTO.class);
			List<ValuesDTO> values = JsonMapper.nonEmptyMapper().fromJson(datas.getData(),type);
			result.put("instrument", instrument);
			result.put("values",values);
			result.put("success",Constant.EXEC_SUCCESS);
		}else {
			result.put("success",Constant.EXEC_FAILED);
			logger.warn("未找到数据：{}",id);
		}
		return result;
	}

	@RequestMapping("export")
	public void downloadExcel(String id, HttpServletRequest request, HttpServletResponse response){

		Datas datas = datasService.queryDatasDetail(id);
		Attachment attachment = attachmentService.queryAttachmentById(datas.getAttachmentId());
		PropertiesLoader loader = new PropertiesLoader("/application.properties");
		String uploadPath = loader.getProperty(Constant.PROPER_KEY_UPLOAD_PATH);
		String fullPath = uploadPath+attachment.getUrl();
		download(request,response,fullPath,attachment.getFileOriginalName());
	}

	@RequestMapping("batchDownload")
	public void batchDownload(String data,HttpServletRequest request,HttpServletResponse response){
        DataDTO dto = JsonMapper.nonEmptyMapper().fromJson(data, DataDTO.class);
		List<DataPlus> dataPluses = datasService.queryDatasNoPage(dto);
		List<String> ids = Lists.newArrayList();
		ArrayList<File> files = Lists.newArrayList();
		for(DataPlus d:dataPluses){
			ids.add(d.getAttachmentId());
		}
		List<Attachment> attachments = attachmentService.queryAttachmentByIds(ids);
		String uploadPath = FileUtil.getDatasAttachmentUploadPath();
		for(Attachment a:attachments){
			File file = new File(uploadPath+a.getUrl());
			files.add(file);
		}
		String zipPath = FileUtil.getZipFilePath();
		String fileName = new DateTime().getMillis()+".zip";
		String dest = ZipUtil.zipFiles(files,zipPath+fileName);
		if(dest != null){
			super.download(request,response,dest,"all_datas.zip");
		}
	}


}
