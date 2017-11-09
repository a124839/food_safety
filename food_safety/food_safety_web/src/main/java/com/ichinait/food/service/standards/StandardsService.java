package com.ichinait.food.service.standards;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Standards;
import com.ichinait.food.db.mapper.AttachmentMapper;
import com.ichinait.food.db.mapper.StandardsMapper;
import com.ichinait.food.db.mapper.plus.StandardsMapperPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ichinait on 16-4-6.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class StandardsService {
    @Autowired
    private StandardsMapper standardsMapper;
    @Autowired
    private StandardsMapperPlus standardsMapperPlus;

    @Autowired
    private AttachmentMapper attachmentMapper;

    public PageInfo<Standards> queryStandardsByConditions(int curr,Standards standards){
        PageHelper.startPage(curr, Constant.PAGE_LIMIT);
        PageHelper.orderBy("s.ct desc");
        List<Standards> standardses = standardsMapperPlus.selectStandardsByConditions(standards);
        PageInfo<Standards> pageInfo = new PageInfo<>(standardses);
        return pageInfo;
    }


    public boolean saveOrUpdate(Standards standards){
        boolean flag = false;
        standards.setUt(new Date());
        if(standards.getId() == null){
            standards.setId(UUID.randomUUID().toString());
            standards.setCt(new Date());
            standards.setStatus(Constant.DATA_VALID_STATE);
            flag = standardsMapper.insert(standards)>0;
        }else{
            flag = standardsMapper.updateByPrimaryKeySelective(standards)>0;
        }
        return flag;
    }


    public Standards queryStandardById(String id){
        return standardsMapper.selectByPrimaryKey(id);
    }

    public boolean deleteStandardById(String id){
//        Standards standards = queryStandardById(id);
//        if(standards != null){
//            String attachmentId = standards.getAttachmentId();
//            if(attachmentId != null){
//                attachmentMapper.deleteByPrimaryKey(attachmentId);
//            }
//        }
        Standards standards = new Standards();
        standards.setId(id);
        standards.setStatus(Constant.DATA_INVALID_STATE);
        return standardsMapper.updateByPrimaryKeySelective(standards)>0;
    }


    /**
     * addBy xiao 2016-04-12
     */
    public void updateStandardsExpiredThread() {
        standardsMapperPlus.updateStandardsExpiredThread();
    }
}
