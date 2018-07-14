/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ps.produce.support;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

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

    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static String sha(String str,String salter){
		byte[] salt = Encodes.decodeHex(salter);
		return  Encodes.encodeHex(Digest.sha1(str.getBytes(), salt, 1024));
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
}
