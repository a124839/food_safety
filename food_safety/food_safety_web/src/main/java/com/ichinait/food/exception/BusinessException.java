package com.ichinait.food.exception;

public class BusinessException extends RuntimeException{
	private String errorCode;
	private String errorMsg;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2545106586030616595L;

	public BusinessException(String errorCode,String errorMsg){
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
