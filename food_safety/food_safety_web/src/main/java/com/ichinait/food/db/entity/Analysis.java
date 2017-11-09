package com.ichinait.food.db.entity;

import java.util.Date;

public class Analysis {
    private String id;

    private String projectId;

    private String algorithmInfo;

    private String operator;

    private Date ct;

    private Date ut;

    private String datasInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getAlgorithmInfo() {
        return algorithmInfo;
    }

    public void setAlgorithmInfo(String algorithmInfo) {
        this.algorithmInfo = algorithmInfo == null ? null : algorithmInfo.trim();
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

    public String getDatasInfo() {
        return datasInfo;
    }

    public void setDatasInfo(String datasInfo) {
        this.datasInfo = datasInfo == null ? null : datasInfo.trim();
    }
}