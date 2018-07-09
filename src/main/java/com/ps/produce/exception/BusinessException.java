package com.ps.produce.exception;

import com.ps.produce.support.ResponseCode;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	private ResponseCode responseCode=ResponseCode.ERROR;
	
	public BusinessException(String message) {
        super(message);
    }
	
	public BusinessException(ResponseCode responseCode) {
        super(responseCode.getName());
        this.responseCode=responseCode;
    }
	
	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}
}


