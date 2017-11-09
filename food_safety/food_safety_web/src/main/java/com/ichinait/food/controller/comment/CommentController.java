package com.ichinait.food.controller.comment;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.ichinait.food.controller.base.BaseController;
import com.ichinait.food.db.entity.Comment;
import com.ichinait.food.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * Created by IChinait on 2016/6/28.
 */
@Controller
@RequestMapping("comment")
public class CommentController extends BaseController<Comment,CommentService> {
    @Autowired
    CommentService commentService;

    @RequestMapping("list")
    public String list(){
        return "/comment/list";
    }


    @RequestMapping("query")
    @ResponseBody
    public Object query(int curr,String name,String analysisId){
        Map<String,Object> result = Maps.newHashMap();
        PageInfo<Comment> pageInfo = commentService.queryPage(curr,name,analysisId);
        super.setSuccess(pageInfo,result);
        return result;
    }




}
