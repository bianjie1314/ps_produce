package com.ps.produce.support;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/** 
 * @author QcQ
 * @date 2015年9月15日 上午11:17:59  
 * @desc 页面提示
 */
public class ShowMessage {
	
	public static void show(RedirectAttributes ra, MessageType msgType, String msg){
		if(MessageType.ISWARNSHOW.equals(msgType)){
			ra.addFlashAttribute("isWarnShow", true);
			ra.addFlashAttribute("warnMessage", "<span style='align:center'>"+msg+"</span>");
		}
		if(MessageType.ISDANGERSHOW.equals(msgType)){
			ra.addFlashAttribute("isDangerShow", true);
			ra.addFlashAttribute("dangerMessage", "<span style='align:center'>"+msg+"</span>");
		}
		if(MessageType.ISSUCCESSSHOW.equals(msgType)){
			ra.addFlashAttribute("isSuccessShow", true);
			ra.addFlashAttribute("successMessage", "<span style='align:center'>"+msg+"</span>");
		}
	}
}
