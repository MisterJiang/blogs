package com.modules.blogs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.blogs.dao.CommentDao;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.mapper.Comment;
import com.modules.blogs.service.CommentService;
import com.utils.DateUtils;
import com.utils.UUID_Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/3/26
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public PageInfo<Comment> findPageList(Comment entity, Integer page, Integer limit) {
        if (page == null){
            page = 1;
        }
        PageHelper.startPage(page, limit, true);
        List<Comment> pageList = commentDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public Integer insert(Comment entity) {
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser((String) SecurityUtils.getSubject().getPrincipal());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        return commentDao.insert(entity);
    }

    @Override
    public Comment get(Comment entity) {
        return commentDao.get(entity);
    }

    @Override
    public Integer delete(Comment entity) {
        return commentDao.delete(entity);
    }

    @Override
    public Integer update(Comment entity) {
        return commentDao.update(entity);
    }
}
