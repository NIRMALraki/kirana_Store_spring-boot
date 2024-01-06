package com.kirana.transaction.custom.exception;

import org.springframework.stereotype.Component;

@Component
public class EmptyInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String erroeMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErroeMessage() {
		return erroeMessage;
	}
	public void setErroeMessage(String erroeMessage) {
		this.erroeMessage = erroeMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public EmptyInputException() {
		super();
	}
	public EmptyInputException(String errorCode, String erroeMessage) {
		super();
		this.errorCode = errorCode;
		this.erroeMessage = erroeMessage;
	}
	
	
}
