package com.ichinait.food.service.comment;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ichinait.food.constant.Constant;
import com.ichinait.food.db.entity.Comment;
import com.ichinait.food.db.mapper.plus.CommentMapperPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by IChinait on 2016/6/28.
 */
@Service
public class CommentService {
    @Autowired
    CommentMapperPlus commentMapperPlus;

    public PageInfo<Comment> queryPage(int curr, String name,String analysisId){
        PageHelper.startPage(curr, Constant.PAGE_LIMIT);
        PageHelper.orderBy("c.ct desc");
        Map<String,Object> param = Maps.newHashMap();
        param.put("name",name);
        param.put("analysisId",analysisId);
        List<Comment> list = commentMapperPlus.selectComment(param);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        return pageInfo;
    }



}
