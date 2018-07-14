package com.ps.produce.shiro;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StreamUtils;

import com.google.common.collect.Maps;
import com.ps.produce.exception.BusinessException;
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

		
       return  true;
	}
	
	
	

}
