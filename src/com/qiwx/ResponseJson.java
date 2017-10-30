package com.qiwx;

import java.io.Serializable;

/**
 * @Author qiwx
 * @time 2017年10月27日 下午7:48:11
 * @Des
 **/
public class ResponseJson<T> implements Serializable {

	private T Data;
	private int Status;
	private String Message;
	public T getData() {
		return Data;
	}
	public void setData(T data) {
		Data = data;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	

}
