package com.ps.produce.support;


/**
 * @author lyqqq
 * @date 2017年10月24日 下午7:13:30 
 */
public class Response {
	
	public Response( ) {
		this.ret = ResponseCode.SUCCESS.value();
		this.msg = ResponseCode.SUCCESS.name();
	}
	public Response(Object data) {
		this();
		this.data = data;
	}
	   public Response(ResponseCode responseCode) {
	        this();
	        this.ret = responseCode.value();
	        this.msg = responseCode.name();
	    }
	
	public Response setResponseCode(ResponseCode code) {
		this.ret = code.value();
		this.msg=code.name();
        return this;
	}

	private int ret;

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	private String msg;
	private Object data;
}
