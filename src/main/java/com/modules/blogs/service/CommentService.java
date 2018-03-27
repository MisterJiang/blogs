package com.modules.blogs.service;

import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.mapper.Comment;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/3/26
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface CommentService {

    PageInfo<Comment> findPageList(Comment entity, Integer page, Integer limit);

    Integer insert(Comment entity);

    Comment get(Comment entity);

    Integer delete(Comment entity);

    Integer update(Comment entity);

}
