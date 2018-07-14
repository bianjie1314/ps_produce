package com.ps.produce.shiro;

import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import com.google.common.collect.Maps;
import com.ps.produce.exception.BusinessException;
import com.ps.produce.support.ResponseCode;
import com.ps.produce.support.SignatureUtils;

public class APIAuthenticationFilter  extends AuthenticatingFilter {

	private static final char SEPARATOR = '_';
	 private static final String CHARSET_NAME = "UTF-8";
	 private static final String SIGNATURE = "signature";
	    
	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {

		String body = IOUtils.toString(request.getInputStream(),"utf-8" );
		Map map = Maps.newTreeMap();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            map.put(name,value);
        }
       return  checkSign(map,body.toString(),"dce7b60efaee20cc");
	}
	
	
	 public Boolean checkSign(Map<String, String> map, String body, String salt) {
		
	        Set<String> keySet = map.keySet();
	        Iterator<String> iter = keySet.iterator();
	        String params = "";
	        String signatureParam = "";
	        boolean isFirstParam = true;
	        while (iter.hasNext()) {
	            String key = iter.next();
	            String value = map.get(key);
	            if (key.equals(SIGNATURE)) {
	                signatureParam = value;
	            } else {
	                if (isFirstParam) {
	                    isFirstParam = false;
	                } else {
	                    params += "&";
	                }
	                params += key + "=" + value;
	            }

	        }

	        params += "&" + body;
	        String signature = SignatureUtils.sha(params, salt);
	        if (!signatureParam.equals(signature)) {
	            throw new BusinessException("验签失败");
	        }
	        return true;
	    }

}
