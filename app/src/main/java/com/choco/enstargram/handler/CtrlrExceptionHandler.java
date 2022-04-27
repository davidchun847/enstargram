package com.choco.enstargram.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.choco.enstargram.handler.exception.CustomApiException;
import com.choco.enstargram.handler.exception.CustomException;
import com.choco.enstargram.handler.exception.CustomValidApiException;
import com.choco.enstargram.handler.exception.CustomValidException;
import com.choco.enstargram.script.JsResponseScript;
import com.choco.enstargram.web.dto.CustomRespDto;

@RestController
@ControllerAdvice
public class CtrlrExceptionHandler {

	@ExceptionHandler(CustomValidException.class)
	public String handleValidException(CustomValidException e) {
		if (e.getErrorMap() == null) {
			return JsResponseScript.alertNback(e.getMessage());
		} else {
			return JsResponseScript.alertNback(e.getErrorMap().toString());
		}
		
	}

	@ExceptionHandler(CustomValidApiException.class)
	public ResponseEntity<?> handleValidApiException(CustomValidApiException e) {
		return new ResponseEntity<>(new CustomRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> handleApiException(CustomApiException e) {
		return new ResponseEntity<>(new CustomRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomException.class)
	public String handleException(CustomException e) {
		return JsResponseScript.alertNback(e.getMessage());
	}
}
