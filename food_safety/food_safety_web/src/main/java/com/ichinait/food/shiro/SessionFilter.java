package com.ichinait.food.shiro;

import com.ichinait.food.constant.Constant;
import com.ichinait.food.exception.ErrorMessage;
import com.ichinait.food.util.JsonMapper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SessionFilter extends AccessControlFilter{
	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		if(!subject.isAuthenticated()){//没登录就不要玩了嘛
			return false;
		}
		String[] roleAry = (String[]) mappedValue;
		if(roleAry== null || roleAry.length == 0){//这个url不需要权限，随便访问
			return true;
		}
		
		for(String role:roleAry){
			if(subject.hasRole(role)){
				logger.debug("==================================防止页面缓存==================================");
				HttpServletResponse res = (HttpServletResponse)response;
				res.setHeader("Expires", "-1");
				res.setHeader("Cache-Control", "no-cache"); //   HTTP/1.1   
				res.setHeader("Pragma", "No-cache"); 
				return true;//有权限，随便玩
			}
		}
		//没权限，不让玩
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		logger.debug("==========用户未登录或没权限，跳转至登录页面。shiro session 拦截器,检查session onAccessDenied=================");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String urlPath = req.getContextPath();
//		String returnUrl = req.getRequestURL().toString();
		if("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))){
			//ajax请求
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("success", Constant.EXEC_FAILED);
			result.put("errorCode", ErrorMessage.SESSION_TIMEOUT.getCode());
			result.put("errorMessage", ErrorMessage.SESSION_TIMEOUT.getMsg());
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(JsonMapper.nonEmptyMapper().toJson(result));
		}else{
			res.sendRedirect(urlPath+"/login.jsp");
		}
		return false;//返回false表示自己已经处理了
		//return true;//返回true表示自己不处理且继续拦截器链执行
	}


}
