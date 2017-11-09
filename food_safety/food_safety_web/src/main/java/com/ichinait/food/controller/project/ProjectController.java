package com.ichinait.food.controller.project;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Project;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.db.entity.plus.ProjectPlus;
import com.ichinait.food.dto.project.ProjectDTO;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.service.project.ProjectService;
import com.ichinait.food.util.JsonMapper;
import com.ichinait.food.util.PropertiesLoader;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("project")
public class ProjectController extends BaseController<Project,ProjectService>{
	private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource
	private ProjectService projectService;
    @Resource
    private AttachmentService attachmentService;
	
	
	@RequestMapping(value="toProjectManagePage",method=RequestMethod.GET)
	public String toProjectManagePage(){
		return "/project/project_list";
	}
	
	@RequestMapping("queryProjectNoPage")
	@ResponseBody
	public Map<String,Object> queryProjectNoPage(){
		Map<String,Object> result = new HashMap<>();
		
		return result;
	}
	
	/**
	 * 分页查询项目列表
	 * @description: 
	 * @param curror
	 * @param projectDto
	 * @return
	 * @author:zhangbo@ichinait.com
	 * @version 1.0
	 * @date 2016年3月7日 上午11:07:14
	 */
	@RequestMapping("queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(@RequestParam(value="curr",defaultValue="1") int curror,ProjectDTO projectDto){
		logger.info("查询项目列表参数：{}",JsonMapper.nonEmptyMapper().toJson(projectDto));
		Map<String,Object> result = new HashMap<>();
		PageInfo<ProjectPlus> pageInfo = projectService.queryProject(curror,projectDto);
		super.setSuccess(pageInfo,result);
		logger.debug("查询项目列表：{}",JsonMapper.nonEmptyMapper().toJson(result));
		return result;
	}
	
	
	@RequestMapping("toAddPage")
	public String toAddPage(){
		return "/project/project_add";
	}
	
	/**
	 * 保存、更新实验信息
	 * @description: 
	 * @return
	 * @author:zhangbo@ichinait.com
	 * @version 1.0
	 * @date 2016年3月25日 下午5:12:51
	 */
	@RequestMapping("save")
	@ResponseBody
	public Map<String,Object> saveProject(Project project,HttpServletRequest request){
		Map<String,Object> result = Maps.newHashMap();
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = req.getFileMap();
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        project.setOperator(user.getId());
        PropertiesLoader loader = new PropertiesLoader("/application.properties");
        String path = loader.getProperty(Constant.PROPER_KEY_PROJECT_ATTACHMENT_UPLOAD_PATH);
        if(fileMap.size()>0){
            for (String key : fileMap.keySet()) {
                MultipartFile file = fileMap.get(key);
                logger.debug("实验附件：{}",file.getOriginalFilename());
                try {
					String attachmentId = attachmentService.saveAttachment(file,user.getId(),path);
					if(key.equals("schemaAttachment")){//实验方案
						project.setSchemaAttachmentId(attachmentId);
					}else{//实验标准
						project.setStandardAttachmentId(attachmentId);
					}
                } catch (Exception e) {
                    result.put("success", Constant.EXEC_ERROR);
                    logger.error("上传文件出错："+ Throwables.getStackTraceAsString(e));
                    return result;
                }
            }
        }
        projectService.saveOrUpdateProject(project);
        result.put("success", Constant.EXEC_SUCCESS);
		return result;
	}
	
	
	/**
	 * 编辑实验信息
	 * @description: 
	 * @param id
	 * @param request
	 * @return
	 * @author:zhangbo@ichinait.com
	 * @version 1.0
	 * @date 2016年3月25日 下午5:33:14
	 */
	@RequestMapping("edit")
	public String edit(String id,HttpServletRequest request){
		Project project = projectService.queryProjectById(id);
		request.setAttribute("project", project);
		return "/project/project_edit";
	}

	@RequestMapping("delete")
    @ResponseBody
	public Map<String,Object> delete(String id){
		Map<String,Object> result = Maps.newHashMap();
		projectService.delete(id);
		result.put("success", Constant.EXEC_SUCCESS);
		return result;
	}


	@RequestMapping("start")
	@ResponseBody
	public Map<String,Object> start(String id){
		Map<String,Object> result = Maps.newHashMap();
		projectService.start(id);
		result.put("success", Constant.EXEC_SUCCESS);
		return result;
	}


	@RequestMapping("finish")
	@ResponseBody
	public Map<String,Object> finish(String id){
		Map<String,Object> result = Maps.newHashMap();
		projectService.finish(id);
		result.put("success", Constant.EXEC_SUCCESS);
		return result;
	}

    @RequestMapping("view")
    public String viewProject(String id,HttpServletRequest request){
        Project project = projectService.queryProjectById(id);
        request.setAttribute("project",project);
        return "/project/project_view";
    }


	/**
	 * 生成实验报告
	 * @param id 实验ID
     */
	@RequestMapping("report")
	public void generateReport(String id, HttpServletResponse response) throws Exception{
		Map<String, Object> map = projectService.generateReport(id);
		Workbook workbook = (Workbook) map.get("workbook");
		try {
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(("实验报告-"+map.get("projectName")+".xlsx").getBytes("utf-8"),"ISO8859-1"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            workbook.close();
        }
    }

}
