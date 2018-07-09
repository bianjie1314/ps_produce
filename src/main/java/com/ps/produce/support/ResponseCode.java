package com.ps.produce.support;

public enum ResponseCode {
	SUCCESS( 0,"请求成功"),
	ERROR(10000,"服务器响应失败"),
	USER_NOT_LOGIN(20001,"用户未登录");

	private final int value;
	private final String name;

	private ResponseCode(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String name(int value) {
		for (ResponseCode state : ResponseCode.values()) {
			if (state.value() == value) {
				return state.name();
			}
		}
		return null;
	}

	public int value() {
		return value;
	}

	public String getName() {
		return name;
	}
}
