package com.ichinait.food.service.type;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Type;
import com.ichinait.food.db.entity.TypeExample;
import com.ichinait.food.db.entity.plus.TypePlus;
import com.ichinait.food.db.mapper.TypeMapper;
import com.ichinait.food.db.mapper.plus.TypeMapperPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ichinait on 2016/4/8.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class TypeService {
    @Autowired
    private TypeMapperPlus typeMapperPlus;
    @Autowired
    private TypeMapper typeMapper;


    public List<Type> queryTypeByParentId(String parentId,int type){
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andParentIdEqualTo(parentId).andTypeEqualTo(type).andStatusEqualTo((int)Constant.DATA_VALID_STATE);
        return typeMapper.selectByExample(typeExample);
    }
    /**
     * 分页查询样品列表
     */
    public PageInfo<TypePlus> queryTypeByConditions(int curror,Map<String,Object> params){
 		PageHelper.startPage(curror, Constant.PAGE_LIMIT);
 		List<TypePlus> types = typeMapperPlus.selectTypeList(params);
 		PageInfo<TypePlus> pageInfo = new PageInfo<TypePlus>(types);
 		return pageInfo;
    }
    /**
     * 删除样品
     */
    public boolean deleteType(String typeId){
    	Type s = new Type();
    	s.setId(typeId);
    	s.setStatus((int)Constant.DATA_INVALID_STATE);
    	return typeMapper.updateByPrimaryKeySelective(s)>0;
    }
    public Type queryTypeById(String id){
        return typeMapper.selectByPrimaryKey(id);
    }
	
		public boolean saveOrUpdateType(Type type){
			boolean flag = false;
			if(Strings.isNullOrEmpty(type.getId())){
				type.setId(UUID.randomUUID().toString());
				type.setCt(new Date());
				type.setUt(new Date());
				type.setStatus((int)Constant.DATA_VALID_STATE);
				if(type.getParentId() == null || type.getParentId().length()<1){
					type.setParentId("0");
				}
				type.setType(1);
				flag = typeMapper.insert(type) > 0;

			}else{
	            type.setUt(new Date());
				flag = typeMapper.updateByPrimaryKeySelective(type) >0;
			
			}
			return flag;
		}

	public PageInfo<Type> queryServiceType(int curr,String id){
		PageHelper.startPage(curr,Constant.PAGE_LIMIT);
//		TypeExample example = new TypeExample();
//        TypeExample.Criteria criteria = example.createCriteria();
//        if(!Strings.isNullOrEmpty(id)){
//            criteria.andIdEqualTo(id);
//		}
//		criteria.andTypeEqualTo(2).andStatusEqualTo((int)Constant.DATA_VALID_STATE);
		List<Type> list = typeMapperPlus.selectServiceType(id);
		PageInfo<Type> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}



	public void saveOrUpdate(Type type){
		type.setUt(new Date());
		if(type.getId() == null){
			type.setId(UUID.randomUUID().toString());
			type.setStatus((int)Constant.DATA_VALID_STATE);
			type.setParentId("0");
			type.setCt(new Date());
			typeMapper.insertSelective(type);
		}else{
			typeMapper.updateByPrimaryKeySelective(type);
//			typeMapper.updateByPrimaryKeyWithBLOBs(type);
		}
	}

	
	public PageInfo<Type> queryFirstType(int curror, String id) {
		PageHelper.startPage(curror,Constant.PAGE_LIMIT);
//		List<Type> list = typeMapperPlus.selectFirstTypeById(id);
//		TypeExample example = new TypeExample();
//		Criteria criteria = example.createCriteria();
//		if(id.length() > 1){
//			criteria.andIdEqualTo(id);
//		}
//		criteria.andStatusEqualTo(1).andTypeEqualTo(1).andParentIdEqualTo("0");

//		List<Type> list = typeMapper.selectByExample(example);

		List<Type> list = typeMapperPlus.selectFirstTypeByConditions(id);
		PageInfo<Type> pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}


	public List<Type> queryServiceTypeByName(String name){
		TypeExample example = new TypeExample();
		example.createCriteria().andNameEqualTo(name).andTypeEqualTo(2).andParentIdEqualTo("0").andStatusEqualTo(1);
		return typeMapper.selectByExample(example);

	}


	public boolean queryTypeByCode(String code){
		TypeExample example = new TypeExample();
		example.createCriteria().andStatusEqualTo(1).andCodeEqualTo(code);
		List<Type> types = typeMapper.selectByExample(example);
		return types != null && types.size()>0;
	}

}
   


