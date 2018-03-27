package com.modules.blogs.service;

import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Comment;
import com.modules.blogs.mapper.Reply;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/3/26
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public interface ReplyService {

    PageInfo<Reply> findPageList(Reply entity, Integer page, Integer limit);

    Integer insert(Reply entity);

    Reply get(Reply entity);

    Integer delete(Reply entity);

    Integer update(Reply entity);
}
