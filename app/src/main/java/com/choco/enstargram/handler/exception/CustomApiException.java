package com.choco.enstargram.handler.exception;

public class CustomApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomApiException(String msg) {
		super(msg);
	}	
}
