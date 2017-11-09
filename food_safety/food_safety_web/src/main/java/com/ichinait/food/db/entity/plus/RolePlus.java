package com.ichinait.food.db.entity.plus;

import java.util.List;

public class RolePlus {
	private String id;
	private List<String> authorities;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}
