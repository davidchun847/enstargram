package com.choco.enstargram.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.choco.enstargram.handler.exception.CustomValidApiException;
import com.choco.enstargram.handler.exception.CustomValidException;

@Component // parent of RestCtrlr, Service
@Aspect
public class ValidAdvice {
	
	@Around("execution(* com.choco.enstargram.web.api.*Ctrlr.*(..))") // before + after decorator
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// proceedingJoinPoint can access the inside of the decorated fcn
		
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg:args) {
			if (arg instanceof BindingResult) {
				System.out.println(arg);
				BindingResult bindRes = (BindingResult) arg;
				
				if (bindRes.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					for (FieldError error : bindRes.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					throw new CustomValidApiException("leave comment failed", errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
	
	@Around("execution(* com.choco.enstargram.web.*Ctrlr.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg:args) {
			if (arg instanceof BindingResult) {
				System.out.println(arg);
				BindingResult bindRes = (BindingResult) arg;
				if (bindRes.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					for (FieldError error : bindRes.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						System.out.println(error.getDefaultMessage());
					}
					throw new CustomValidException("singup validation failed", errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
