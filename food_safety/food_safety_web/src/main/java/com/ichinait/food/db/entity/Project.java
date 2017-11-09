package com.ichinait.food.db.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Project {
    private String id;

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String schemaAttachmentId;

    private String standardAttachmentId;

    private String operator;

    private Byte status;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSchemaAttachmentId() {
        return schemaAttachmentId;
    }

    public void setSchemaAttachmentId(String schemaAttachmentId) {
        this.schemaAttachmentId = schemaAttachmentId == null ? null : schemaAttachmentId.trim();
    }

    public String getStandardAttachmentId() {
        return standardAttachmentId;
    }

    public void setStandardAttachmentId(String standardAttachmentId) {
        this.standardAttachmentId = standardAttachmentId == null ? null : standardAttachmentId.trim();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return id != null ? id.equals(project.id) : project.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}