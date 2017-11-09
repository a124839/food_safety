package com.ichinait.food.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Sample {
    private String id;

    private String code;

    private String name;

    private String categoryLv1Id;

    private String categoryLv2Id;

    private String producingArea;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date productionDate;

    private String manufactor;

    private String batches;

    private String memo;

    private String operator;

    private Byte status;
    
    private List<SampleIndicator> sampleIndicators;

    private Date ct;

    private Date ut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategoryLv1Id() {
        return categoryLv1Id;
    }

    public void setCategoryLv1Id(String categoryLv1Id) {
        this.categoryLv1Id = categoryLv1Id == null ? null : categoryLv1Id.trim();
    }

    public String getCategoryLv2Id() {
        return categoryLv2Id;
    }

    public void setCategoryLv2Id(String categoryLv2Id) {
        this.categoryLv2Id = categoryLv2Id == null ? null : categoryLv2Id.trim();
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea == null ? null : producingArea.trim();
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor == null ? null : manufactor.trim();
    }

    public String getBatches() {
        return batches;
    }

    public void setBatches(String batches) {
        this.batches = batches == null ? null : batches.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public List<SampleIndicator> getSampleIndicators() {
        return sampleIndicators;
    }

    public void setSampleIndicators(List<SampleIndicator> sampleIndicators) {
        this.sampleIndicators = sampleIndicators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sample sample = (Sample) o;

        return id != null ? id.equals(sample.id) : sample.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}