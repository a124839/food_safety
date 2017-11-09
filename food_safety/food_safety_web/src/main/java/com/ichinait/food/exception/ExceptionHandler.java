package com.ichinait.food.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;
import com.ichinait.food.util.JsonMapper;

public class ExceptionHandler implements HandlerExceptionResolver{
	private static Logger logger = LoggerFactory.getLogger(HandlerExceptionResolver.class);
	
	/**
	 * 这里处理两种异常，一个是业务异常，一个是系统异常
	 * 业务异常需要定义errorCode和errorMsg
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		Map<String,Object> result = new HashMap<String,Object>();
		arg1.setCharacterEncoding("UTF-8");
		arg1.setContentType("text/html");
		try {
			if (arg3 instanceof BusinessException) {
				BusinessException be = (BusinessException)arg3;
				logger.error("捕获到业务异常：{}",Throwables.getStackTraceAsString(arg3));
				result.put("success", true);
				result.put("errorCode", be.getErrorCode());
				result.put("errorMsg", be.getErrorMsg());
			}else{
				logger.error("捕获到系统异常：{}",Throwables.getStackTraceAsString(arg3));
				result.put("success", false);
				result.put("errorCode", ErrorMessage.SYSTEM_ERROR.getCode());
				result.put("errorMsg", ErrorMessage.SYSTEM_ERROR.getMsg());
			}
			PrintWriter writer = arg1.getWriter();
			writer.write(JsonMapper.nonEmptyMapper().toJson(result));
			writer.flush();
		} catch (IOException e) {
			logger.error("异常转JSON的时候都出错了...{}",Throwables.getStackTraceAsString(e));
		}
		return null;
	}

}
