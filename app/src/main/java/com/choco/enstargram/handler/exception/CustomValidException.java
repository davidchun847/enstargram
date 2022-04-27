package com.choco.enstargram.handler.exception;

import java.util.Map;

public class CustomValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errorMap;
	
	public CustomValidException(String msg) {
		super(msg);
	}
	
	public CustomValidException(String msg, Map<String, String> errorMap) {
		super(msg);
		this.errorMap = errorMap;
	}
	
	public Map<String, String> getErrorMap() {
		return this.errorMap;
	}
}
