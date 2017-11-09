package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by IChinait on 2016/6/28.
 */
@Repository
public interface CommentMapperPlus {
    List<Comment> selectComment(Map<String, Object> params);
}
