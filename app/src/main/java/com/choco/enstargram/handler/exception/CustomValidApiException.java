package com.choco.enstargram.handler.exception;

import java.util.Map;

public class CustomValidApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap;

	public CustomValidApiException(String msg) {
		super(msg);
	}
	
	public CustomValidApiException(String msg, Map<String, String> errorMap) {
		super(msg);
		this.errorMap = errorMap;
	}
	
	public Map<String, String> getErrorMap() {
		return this.errorMap;
	}
}
