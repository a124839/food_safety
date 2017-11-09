package com.ichinait.food.db.entity;

import java.util.Date;

public class Datas {
    private String id;

    private String instrumentId;

    private String sampleId;

    private String projectId;

    private String attachmentId;

    private String resolution;

    private String wavelengthRange;

    private Integer scanningTimes;

    private String scanningDuration;

    private Date ct;

    private Date ut;

    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId == null ? null : instrumentId.trim();
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId == null ? null : sampleId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId == null ? null : attachmentId.trim();
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution == null ? null : resolution.trim();
    }

    public String getWavelengthRange() {
        return wavelengthRange;
    }

    public void setWavelengthRange(String wavelengthRange) {
        this.wavelengthRange = wavelengthRange == null ? null : wavelengthRange.trim();
    }

    public Integer getScanningTimes() {
        return scanningTimes;
    }

    public void setScanningTimes(Integer scanningTimes) {
        this.scanningTimes = scanningTimes;
    }

    public String getScanningDuration() {
        return scanningDuration;
    }

    public void setScanningDuration(String scanningDuration) {
        this.scanningDuration = scanningDuration == null ? null : scanningDuration.trim();
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}