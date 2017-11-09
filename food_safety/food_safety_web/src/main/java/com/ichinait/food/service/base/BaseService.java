package com.ichinait.food.service.base;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.dto.base.BaseDTO;

public abstract class BaseService<T,M> {
	
	@Autowired
	private M m;
	
	
	public PageInfo<T> queryByConditions(Integer currsor,BaseDTO dto){
		PageHelper.startPage(currsor, 15);
		List<T> list = queryByCondition(currsor, dto);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}
	
	@SuppressWarnings("unchecked")
	public T get(Object id){
		try {
			if(id != null){
				Method method = m.getClass().getMethod(Constant.SELECT_BY_PRIMARY_KEY, id.getClass());
				Object obj = method.invoke(m,id);
				if(obj != null){
					return (T)obj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public int delete(Object id) throws Exception{
		Method method = m.getClass().getMethod(Constant.DELETE_BY_PRIMARY_KEY,Object.class);
		Object obj = method.invoke(m, id);
		if(obj != null){
			return (int)obj;
		}
		return 0;
	}
	
	public int insert(T t) throws Exception{
		return invoke(Constant.INSERT,t);
	}
	
	public int insertSelective(T t) throws Exception{
		return invoke(Constant.INSERT_SELECTIVE,t);
	}
	
	
	public int update(T t) throws Exception{
		return invoke(Constant.UPDATE_BY_PRIMARY_KEY,t);
	}
	
	public int updateSelective(T t) throws Exception{
		return invoke(Constant.UPDATE_BY_PRIMARY_KEY_SELECTIVE,t);
	}
	
	private int invoke(String methodName,T t) throws Exception{
		Method method = m.getClass().getMethod(methodName,t.getClass());
		Object obj = method.invoke(m, t);
		if(obj != null){
			return (int)obj;
		}
		return 0;
	}
	
	
	
	
	 abstract  public List<T> queryByCondition(int currsor,BaseDTO dto);

}
