package com.ichinait.food.service.attachment;

import com.google.common.io.Files;
import com.ichinait.food.db.entity.Attachment;
import com.ichinait.food.db.mapper.AttachmentMapper;
import com.ichinait.food.db.mapper.plus.AttachmentMapperPlus;
import com.ichinait.food.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ichinait on 16-3-24.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private AttachmentMapperPlus attachmentMapperPlus;


    public Attachment queryAttachmentById(String id){
        return attachmentMapper.selectByPrimaryKey(id);
    }


    public List<Attachment> queryAttachmentByIds(List<String> ids){
        return attachmentMapperPlus.selectAttachmentsByIds(ids);
    }

    public String saveDatasFile(MultipartFile file, String uploader, String savePath, String url) throws  Exception{
        FileUtil.save(file,uploader,savePath);
        String fileOriginalName = file.getOriginalFilename();//文件的真实名字
        long fileSize = file.getSize()/1024;
        String type = Files.getFileExtension(fileOriginalName);
        String fileName = uploader+'-'+new Date().getTime();//文件保存的名字
        String id = UUID.randomUUID().toString();
        Attachment attachment = new Attachment();
        attachment.setId(id);
        attachment.setFileOriginalName(file.getOriginalFilename());
        attachment.setFileName(fileName);
        attachment.setUploader(uploader);
        attachment.setFileSize(fileSize);
        attachment.setFileType(type);
        attachment.setUrl(url);//文件访问地址
        attachment.setCt(new Date());
        attachment.setUt(new Date());
        attachmentMapper.insert(attachment);
        return  id;
    }

    public String saveAttachment(MultipartFile file, String uploader, String savePath) throws  Exception{
        String fileOriginalName = file.getOriginalFilename();//文件的真实名字
        long fileSize = file.getSize()/1024;
        String type = Files.getFileExtension(fileOriginalName);
        String fileName = uploader+'-'+new Date().getTime();//文件保存的名字
        String id = UUID.randomUUID().toString();
        FileUtil.save(file,savePath,fileName+"."+type);
        Attachment attachment = new Attachment();
        attachment.setId(id);
        attachment.setFileOriginalName(file.getOriginalFilename());
        attachment.setFileName(fileName);
        attachment.setUploader(uploader);
        attachment.setFileSize(fileSize);
        attachment.setFileType(type);
        attachment.setUrl(fileName+"."+type);//文件访问地址
        attachment.setCt(new Date());
        attachment.setUt(new Date());
        attachmentMapper.insert(attachment);
        return  id;
    }
}
