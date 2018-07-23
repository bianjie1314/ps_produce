package com.ps.produce.support.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.ps.produce.order.dto.OrderParam;
import com.ps.produce.support.Response;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.SignatureUtils;

public class StateUtils {
	public static  Response changState(String orderNo,String states,String tracking_number,String carriers,String urlHead) {
    	Gson gson=new Gson();
    	OrderParam o=new OrderParam();
    	o.setCarriers(carriers);
    	o.setOrder_no(orderNo);
    	o.setState(states);
    	o.setTracking_number(tracking_number);
    	String data =gson.toJson(o);
    	String url=urlHead+"/ps/orders/sync";
    	url = SignatureUtils.signatureUrl(url, data, "dce7b60efaee20cc");
    	Response res = new Response();
    	try {
    	String response=HttpClientUtil.doPostJson(url,data);
    	res=  gson.fromJson(response,Response.class);
    	}catch(Exception e){
    		res.setRet(ResponseCode.ERROR.value());
    		res.setMsg("订单同步异常");
    	}
    	return res;
      
    }
	public static void main(String[] args) {
		
	}
}
