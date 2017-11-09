package com.ichinait.food.controller.standards;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Standards;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.service.standards.StandardsService;
import com.ichinait.food.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ichinait on 16-4-6.
 */
@Controller
@RequestMapping("standards")
public class StandardsController extends BaseController<Standards,StandardsService>{

    private static Logger logger = LoggerFactory.getLogger(StandardsController.class);

    @Autowired
    private StandardsService standardsService;

    @Autowired
    private AttachmentService attachmentService;


    @RequestMapping("toQueryPage")
    public String toQueryPage(){
        return "/standards/standards_list";
    }

    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> query(int curr,Standards standards){
        Map<String,Object> result = Maps.newHashMap();
        PageInfo<Standards> pageInfo = standardsService.queryStandardsByConditions(curr,standards);
        super.setSuccess(pageInfo,result);
        return result;
    }

    @RequestMapping("toAddPage")
    public String toAddPage(){

        return "/standards/standard_add";
    }

    @RequestMapping("add")
    public String add(Standards standards, HttpServletRequest request){
        MultipartHttpServletRequest res = (MultipartHttpServletRequest) request;
        MultipartFile file =  res.getFileMap().get("attachment");
        String savePath = FileUtil.getStandardAttachmentUploadPath();
        User user = super.getSessionUser();
        try {
            if(file != null){
                String attachmentId = attachmentService.saveAttachment(file, user.getId(), savePath);
                standards.setAttachmentId(attachmentId);
            }
            standards.setOperator(user.getId());
            standardsService.saveOrUpdate(standards);
        } catch (Exception e) {
            logger.error("添加标准出错：{}"+Throwables.getStackTraceAsString(e));
        }
        return "/standards/standards_list";

    }

    @RequestMapping("toEditPage")
    public String toEditPage(String id,HttpServletRequest request){
        Standards standards = standardsService.queryStandardById(id);
        request.setAttribute("standards",standards);
        return "/standards/standard_edit";
    }

    @RequestMapping("view")
    public String view(String id,HttpServletRequest request){
        Standards standards = standardsService.queryStandardById(id);
        request.setAttribute("standards",standards);
        return  "/standards/standard_view";
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String,Object> delete(String id){
        Map<String,Object> result= Maps.newHashMap();
        standardsService.deleteStandardById(id);
        result.put("success", Constant.EXEC_SUCCESS);
        return result;
    }

}
