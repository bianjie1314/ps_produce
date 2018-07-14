/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.support;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.ps.produce.exception.BusinessException;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author ThinkGem
 * @version 2013-05-22
 */
public class SignatureUtils extends org.apache.commons.lang3.StringUtils {
    private static Logger logger = Logger.getLogger(SignatureUtils.class);

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    private static final String SIGNATURE = "signature";
    private static final String salt = "dce7b60efaee20cc";
    /**
     * 转换为字节数组
     * @param str
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String sha(String str,String salter) {
		byte[] salt = Encodes.decodeHex(salter);
      try {
		return  Encodes.encodeHex(Digests.sha1(str.getBytes("UTF-8"), salt, 1024));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return null;
    }
    
 

    public static String signatureUrl(String url, String data, String salt) {
        Long timestamp = System.currentTimeMillis();
        Map<String, String> map = Maps.newTreeMap();
        map.put("timestamp", timestamp.toString());

        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        String params = "";
        boolean isFirstParam = true;

        while (iter.hasNext()) {
            String key = iter.next();
            String value = map.get(key);
            if (isFirstParam) {
                isFirstParam = false;
            } else {
                params += "&";
            }
            params += key + "=" + value;
        }
        url += "?" + params;
        params += "&" + data;

        String signature = SignatureUtils.sha(params, salt);
        url += "&" + SIGNATURE + "=" + signature;
        logger.info("signatureUrl to url:" + url + "data:" + data);
        return url;
    }
    
    public static Boolean checkSign(HttpServletRequest request,String body ) {
    	
    	Map map = Maps.newTreeMap();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            map.put(name,value);
        }
        return checkSign(map,body,salt);
    }
    
    public static Boolean checkSign(Map<String, String> map, String body, String salt) {
		
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
