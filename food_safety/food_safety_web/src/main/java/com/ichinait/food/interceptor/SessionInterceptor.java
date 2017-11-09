package com.ichinait.food.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ichinait.food.constant.Constant;
import com.ichinait.food.exception.ErrorMessage;
import com.ichinait.food.util.JsonMapper;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("=========session 拦截器================");
		String urlPath = request.getContextPath();
		Object obj = request.getSession().getAttribute("user");
		if(obj == null){
			logger.debug("用户未登录，跳转至登录页面");
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				//ajax请求
				Map<String,Object> result = new HashMap<String,Object>();
				result.put("success", Constant.EXEC_FAILED);
				result.put("errorCode", ErrorMessage.SESSION_TIMEOUT.getCode());
				result.put("errorMessage", ErrorMessage.SESSION_TIMEOUT.getMsg());
				response.getWriter().write(JsonMapper.nonEmptyMapper().toJson(result));
			}else{
				response.sendRedirect(urlPath+"/login.jsp");
			}
		}
		return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("==================================防止页面缓存==================================");
		response.setHeader("Expires", "-1");
		response.setHeader("Cache-Control", "no-cache"); //   HTTP/1.1   
		response.setHeader("Pragma", "No-cache"); 
		super.afterCompletion(request, response, handler, ex);
	}

}
