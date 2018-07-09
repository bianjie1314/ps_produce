package com.ps.produce.web.filter;
//package com.sop.web.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//
//import com.google.gson.Gson;
//import com.sop.exception.BusinessException;
//import com.sop.support.Response;
//import com.sop.support.ResponseCode;
//
//public class ExceptionHandlerFilter implements Filter {
//	private static Logger logger = Logger.getLogger(ExceptionHandlerFilter.class);
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		try {
//			filterChain.doFilter(request, response);
//		} catch (Exception ex) {
//			logger.error(ex);
//			if(isAjax((HttpServletRequest)request)){
//				Response res = new Response();
//				if (ex.getCause() instanceof BusinessException) {
//					BusinessException bx = (BusinessException) ex.getCause();
//					res.setResponseCode(bx.getResponseCode());
//					res.setMsg(bx.getMessage()); 
//				} else {
//					res.setResponseCode(ResponseCode.ERROR);
//					res.setMsg("啊哦，系统出错啦~");
//					res.setData(ex.getMessage());
//				}
//				httpResponse.setContentType("application/json;charset=UTF-8");
//				Gson g = new Gson();
//				httpResponse.getWriter().write(g.toJson(res));
//			}else{
////				throw ex;
//				filterChain.doFilter(request, response);
//			}
//		}
//	}
//
//	boolean isAjax(HttpServletRequest request){
//	    return  (request.getHeader("X-Requested-With") != null  
//	    && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) ;
//	}
//	
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//}
