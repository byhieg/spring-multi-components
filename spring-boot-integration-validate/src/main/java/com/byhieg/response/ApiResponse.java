package com.byhieg.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by byhieg on 2023/4/15.
 * Mail to byhieg@gmail.com
 */
@Data
public class ApiResponse<T>{
	
	private boolean success;
	
	private T data;
	
	private String errorMsg;
	
	private int code;


	public static <T> ApiResponse<T> error(String errorMsg, T data) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setCode(HttpStatus.BAD_REQUEST.value());
		response.setErrorMsg(errorMsg);
		response.setSuccess(false);
		return response;
	}
}
