package com.ichinait.food.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Instrument {
    private String id;

    private String name;

    private String model;

    private String category;

    private String type;

    private String sn;

    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    private String price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date installEndDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date installStartDate;

    private String y;

    private String x;

    private String manufactor;

    private String attachmentId;

    private Byte status;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getInstallEndDate() {
        return installEndDate;
    }

    public void setInstallEndDate(Date installEndDate) {
        this.installEndDate = installEndDate;
    }

    public Date getInstallStartDate() {
        return installStartDate;
    }

    public void setInstallStartDate(Date installStartDate) {
        this.installStartDate = installStartDate;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor == null ? null : manufactor.trim();
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId == null ? null : attachmentId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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