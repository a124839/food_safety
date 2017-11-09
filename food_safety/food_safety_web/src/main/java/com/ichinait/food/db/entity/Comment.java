package com.ichinait.food.db.entity;

import java.util.Date;

public class Comment {
    private String id;

    private String name;

    private String jmyps;

    private String mxzcfs;

    private String rmscv;

    private String rR;

    private String memo;

    private String analysisId;

    private String modalAttachmentId;

    private String dataAttachmentId;

    private String operator;

    private Date ct;

    private Date ut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJmyps() {
        return jmyps;
    }

    public void setJmyps(String jmyps) {
        this.jmyps = jmyps == null ? null : jmyps.trim();
    }

    public String getMxzcfs() {
        return mxzcfs;
    }

    public void setMxzcfs(String mxzcfs) {
        this.mxzcfs = mxzcfs == null ? null : mxzcfs.trim();
    }

    public String getRmscv() {
        return rmscv;
    }

    public void setRmscv(String rmscv) {
        this.rmscv = rmscv == null ? null : rmscv.trim();
    }

    public String getrR() {
        return rR;
    }

    public void setrR(String rR) {
        this.rR = rR == null ? null : rR.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId == null ? null : analysisId.trim();
    }

    public String getModalAttachmentId() {
        return modalAttachmentId;
    }

    public void setModalAttachmentId(String modalAttachmentId) {
        this.modalAttachmentId = modalAttachmentId == null ? null : modalAttachmentId.trim();
    }

    public String getDataAttachmentId() {
        return dataAttachmentId;
    }

    public void setDataAttachmentId(String dataAttachmentId) {
        this.dataAttachmentId = dataAttachmentId == null ? null : dataAttachmentId.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public Date getUt() {
        return ut;
    }

    public void setUt(Date ut) {
        this.ut = ut;
    }
}