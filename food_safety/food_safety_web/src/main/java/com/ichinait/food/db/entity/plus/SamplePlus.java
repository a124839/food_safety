package com.ichinait.food.db.entity.plus;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ichinait.food.db.entity.SampleIndicator;

public class SamplePlus {
	private String id;

    private String code;

    private String name;

    private String categoryLv1Id;
    private String categoryLv1Name;
    private String categoryLv2Id;
    private String categoryLv2Name;
    

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryLv1Id() {
		return categoryLv1Id;
	}

	public void setCategoryLv1Id(String categoryLv1Id) {
		this.categoryLv1Id = categoryLv1Id;
	}

	public String getCategoryLv1Name() {
		return categoryLv1Name;
	}

	public void setCategoryLv1Name(String categoryLv1Name) {
		this.categoryLv1Name = categoryLv1Name;
	}

	public String getCategoryLv2Id() {
		return categoryLv2Id;
	}

	public void setCategoryLv2Id(String categoryLv2Id) {
		this.categoryLv2Id = categoryLv2Id;
	}

	public String getCategoryLv2Name() {
		return categoryLv2Name;
	}

	public void setCategoryLv2Name(String categoryLv2Name) {
		this.categoryLv2Name = categoryLv2Name;
	}

	public String getProducingArea() {
		return producingArea;
	}

	public void setProducingArea(String producingArea) {
		this.producingArea = producingArea;
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
		this.manufactor = manufactor;
	}

	public String getBatches() {
		return batches;
	}

	public void setBatches(String batches) {
		this.batches = batches;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public List<SampleIndicator> getSampleIndicators() {
		return sampleIndicators;
	}

	public void setSampleIndicators(List<SampleIndicator> sampleIndicators) {
		this.sampleIndicators = sampleIndicators;
	}

	public Date getCt() {
		return ct;
	}

	public void setCt(Date ct) {
		this.ct = ct;
	}
    

}
