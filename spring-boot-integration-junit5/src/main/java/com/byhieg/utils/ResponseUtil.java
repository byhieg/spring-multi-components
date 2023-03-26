package com.byhieg.utils;

import com.byhieg.entity.WebResponse;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class ResponseUtil {

	public static final String SUCCESS_STATUS = "success";
	public static final String FAILURE_STATUS = "failure";

	public static <T> WebResponse<T> OK(T data){
		WebResponse<T> webResponse = new WebResponse<>();
		webResponse.setStatus(SUCCESS_STATUS);
		webResponse.setCode(200);
		webResponse.setData(data);
		return webResponse;
	}

	public static <T> WebResponse<T> FAIL(T data,String errorMsg){
		WebResponse<T> webResponse = new WebResponse<>();
		webResponse.setStatus(FAILURE_STATUS);
		webResponse.setCode(500);
		webResponse.setData(data);
		webResponse.setErrorMsg(errorMsg);
		return webResponse;
	}
}
