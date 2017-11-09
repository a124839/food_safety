package com.ichinait.food.controller.base;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.User;
import com.ichinait.food.dto.base.BaseDTO;
import com.ichinait.food.exception.ErrorMessage;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class BaseController<T,S>{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private S s;


	public <T> void setSuccess(PageInfo<T> pageInfo,Map<String,Object> result){
		result.put("recordsTotal", pageInfo.getTotal());//总记录数
		result.put("totalPages", pageInfo.getPages());//总页数
		result.put("list", pageInfo.getList());
		result.put("success", Constant.EXEC_SUCCESS);
	}
	
	public void setError(Exception e,Map<String,Object> result){
		result.put("success", Constant.EXEC_ERROR);
		result.put("errorCode", ErrorMessage.SYSTEM_ERROR.getCode());
		result.put("errorMessage", ErrorMessage.SYSTEM_ERROR.getMsg());
	}

	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @param filePath 文件本地全路径
	 * @param fileName 要保存的文件名称
     */
	public void download(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName){
		try {
			Path path = Paths.get(filePath);
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"),"ISO8859-1"));
			response.addHeader("Content-Length", "" + path.toFile().length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(Files.readAllBytes(path));
			toClient.flush();
			toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public User getSessionUser(){
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		return user;
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryList(int currsor,BaseDTO dto){
		Map<String,Object> result = Maps.newHashMap();
		try {
			Method method = s.getClass().getMethod("queryByConditions",Integer.class, BaseDTO.class);
			PageInfo<T> pageInfo = (PageInfo<T>) method.invoke(s, currsor,dto);
			result.put("recordsTotal", pageInfo.getTotal());//总记录数
			result.put("totalPages", pageInfo.getPages());//总页数
			result.put("list", pageInfo.getList());
			result.put("success", Constant.EXEC_SUCCESS);
		} catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
			result.put("success", Constant.EXEC_ERROR);
			result.put("errorCode", ErrorMessage.SYSTEM_ERROR.getCode());
			result.put("errorMessage", ErrorMessage.SYSTEM_ERROR.getMsg());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public T queryById(Object id) throws Exception{
		Method method = s.getClass().getMethod("get", Object.class);
		Object obj = method.invoke(s, id);
		if(obj != null){
			return (T)obj;
		}
		return null;
	}
	
	
	public int insert(T t) throws Exception{
		Method method = s.getClass().getMethod("insert", Object.class);
		Object obj = method.invoke(s, t);
		return (int) obj;
	}

}
