package com.choco.enstargram.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomRespDto<T> {
	
	private int code; // 1: success, -1: failure
	private String message;
	private T data;

}
