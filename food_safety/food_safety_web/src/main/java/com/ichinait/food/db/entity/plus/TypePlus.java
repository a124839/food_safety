package com.ichinait.food.db.entity.plus;

import java.util.Date;

public class TypePlus {
	private String id;
	private String code;
	private String categoryLv1Name;
	private String categoryLv2Name;

	private String operator;

	private Date ct;
	private String memo;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategoryLv1Name() {
		return categoryLv1Name;
	}

	public void setCategoryLv1Name(String categoryLv1Name) {
		this.categoryLv1Name = categoryLv1Name;
	}

	public String getCategoryLv2Name() {
		return categoryLv2Name;
	}

	public void setCategoryLv2Name(String categoryLv2Name) {
		this.categoryLv2Name = categoryLv2Name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCt() {
		return ct;
	}

	public void setCt(Date ct) {
		this.ct = ct;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
