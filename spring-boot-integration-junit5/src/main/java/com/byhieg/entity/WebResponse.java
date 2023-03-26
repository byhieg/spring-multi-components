package com.byhieg.entity;

/**
 * Created by byhieg on 2023/3/26.
 * Mail to byhieg@gmail.com
 */
public class WebResponse<T> {
	
	private int code;
	private T data;
	private String status;
	
	private String errorMsg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
