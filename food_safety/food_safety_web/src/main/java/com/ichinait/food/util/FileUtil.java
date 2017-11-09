package com.ichinait.food.util;

import com.google.common.io.Files;
import com.ichinait.food.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by ichinait on 2016/3/29.
 */
public class FileUtil {
    private  static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    private static PropertiesLoader loader = new PropertiesLoader("/application.properties");
    /**
     *
     * @param file
     * @param path 保存路径
     * @param fileFullName 带后缀的要保存的文件名
     * @throws IOException
     */
    public static void save(MultipartFile file,String path,String fileFullName) throws IOException {
        String fileOriginalName = file.getOriginalFilename();//文件的真实名字
        long fileSize = file.getSize()/1024;
        File p = new File(path);
        if(!p.exists()){
            p.mkdirs();
        }
        File to = new File(path+fileFullName);
        logger.info("文件保存路径：{}",to.getPath());
        Files.write(file.getBytes(), to);//保存本地
    }

    public static String getProjectAttachmentUploadPath(){
        String path = loader.getProperty(Constant.PROPER_KEY_PROJECT_ATTACHMENT_UPLOAD_PATH);
        return path;
    }
    public static String getInstrumentAttachmentUploadPath(){
        String path = loader.getProperty(Constant.PROPER_KEY_INSTRUMENT_ATTACHMENT_UPLOAD_PATH);
        return path;
    }
    public static String getAlgorithmAttachmentUploadPath(){
        String path = loader.getProperty(Constant.PROPER_KEY_ALGORITHM_ATTACHMENT_UPLOAD_PATH);
        return path;
    }

    public static String getDatasAttachmentUploadPath(){
        String path = loader.getProperty(Constant.PROPER_KEY_UPLOAD_PATH);
        return path;
    }

    public static String getStandardAttachmentUploadPath(){
        String path = loader.getProperty(Constant.PROPER_KEY_STANDARD_ATTACHMENT_UPLOAD_PATH);
        return path;
    }

    public static String getModalAttachmentUploadPath(){
        String path = loader.getProperty(Constant.PROPER_KEY_COMMENT_ATTACHMENT_UPLOAD_PATH);
        return path;
    }

    public static String getZipFilePath(){
        String path = loader.getProperty(Constant.PROPER_KEY_ZIP_PATH);
        return path;
    }

}
