package com.ps.produce.support;
/** 
 * @author QcQ
 * @date 2015年11月24日 下午1:47:59  
 * @desc 
 */
public enum MessageType {
	ISWARNSHOW("isWarnShow"), ISDANGERSHOW("isDangerShow"), ISSUCCESSSHOW("isSuccessShow");
	
	
	public String msgType;
	
	private MessageType (String msgType){
		this.msgType = msgType;
	}

}
