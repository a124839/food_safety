package com.ichinait.food.dto.analysis;

import org.springframework.web.multipart.MultipartFile;

public class CommentDTO {
    private String analysisId;

    private String name;
    

    //建模样品数
    private String jmyps;
    //模型主成分数
    private String zcfs;
    //r^2 决定系数
    private String r;
    //交叉验证均方根误差
    private String rmscv;
    
    private String memo;

    private  String uploader;

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    private MultipartFile modalFile;

    private MultipartFile dataFile;

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getRmscv() {
        return rmscv;
    }

    public void setRmscv(String rmscv) {
        this.rmscv = rmscv;
    }

    public MultipartFile getModalFile() {
        return modalFile;
    }

    public void setModalFile(MultipartFile modalFile) {
        this.modalFile = modalFile;
    }

    public MultipartFile getDataFile() {
        return dataFile;
    }

    public void setDataFile(MultipartFile dataFile) {
        this.dataFile = dataFile;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJmyps() {
        return jmyps;
    }
    public void setJmyps(String jmyps) {
        this.jmyps = jmyps;
    }
    public String getZcfs() {
        return zcfs;
    }
    public void setZcfs(String zcfs) {
        this.zcfs = zcfs;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    

}
