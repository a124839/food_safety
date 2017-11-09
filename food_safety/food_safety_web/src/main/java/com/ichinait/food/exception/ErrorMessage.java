package com.ichinait.food.exception;

public enum ErrorMessage {
	
	SYSTEM_ERROR("1000","系统内部错误"),
	LOGIN_ERROR("1001","用户名或密码错误 "),
	IMPORT_ERROR("1004","导入的数据没有找到对应的样品！"),
	ROLE_ERROR("1003","请联系管理员为此用户分配权限！ "),
	SESSION_TIMEOUT("1002","会话超时")
	;
	private String code;
	private String msg;
	
	ErrorMessage(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
