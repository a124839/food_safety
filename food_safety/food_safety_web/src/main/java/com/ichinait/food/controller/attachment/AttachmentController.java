package com.ichinait.food.controller.attachment;

import com.fasterxml.jackson.databind.JavaType;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Attachment;
import com.ichinait.food.service.attachment.AttachmentService;
import com.ichinait.food.util.FileUtil;
import com.ichinait.food.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ichinait on 16-3-31.
 */
@Controller
@RequestMapping("attachment")
public class AttachmentController extends BaseController<Attachment,AttachmentService>{
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("download")
    public void download(@RequestParam(name = "attachmentId") String id,String type, HttpServletRequest request, HttpServletResponse response){
        Attachment attachment = attachmentService.queryAttachmentById(id);
        String uploadPath = "";
        switch (type){
            case "project":
                uploadPath = FileUtil.getProjectAttachmentUploadPath();
                break;
            case "instrument":
                uploadPath = FileUtil.getInstrumentAttachmentUploadPath();
                break;
            case "algorithm":
                uploadPath = FileUtil.getAlgorithmAttachmentUploadPath();
                break;
            case "standard":
                uploadPath = FileUtil.getStandardAttachmentUploadPath();
                break;
            case "modal":
                uploadPath = FileUtil.getModalAttachmentUploadPath();
                break;
            default:
                uploadPath = FileUtil.getDatasAttachmentUploadPath();
        }
        String fullPath = uploadPath+attachment.getUrl();
        super.download(request,response,fullPath,attachment.getFileOriginalName());
    }

    @RequestMapping("downloadZip")
    public void downloadMulti(String data){
        JavaType javaType = JsonMapper.nonEmptyMapper().constructCollectionType(List.class,String.class);
        List<String> ids = JsonMapper.nonEmptyMapper().fromJson(data,javaType);
        List<Attachment> attachments = attachmentService.queryAttachmentByIds(ids);

    }
}
