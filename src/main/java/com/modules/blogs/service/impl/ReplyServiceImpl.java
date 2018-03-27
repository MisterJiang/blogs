package com.modules.blogs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.blogs.dao.CommentDao;
import com.modules.blogs.dao.ReplyDao;
import com.modules.blogs.mapper.Comment;
import com.modules.blogs.mapper.Reply;
import com.modules.blogs.service.ReplyService;
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
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDao replyDao;

    @Override
    public PageInfo<Reply> findPageList(Reply entity, Integer page, Integer limit) {
        if (page == null){
            page = 1;
        }
        PageHelper.startPage(page, limit, true);
        List<Reply> pageList = replyDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public Integer insert(Reply entity) {
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser((String) SecurityUtils.getSubject().getPrincipal());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        return replyDao.insert(entity);
    }

    @Override
    public Reply get(Reply entity) {
        return replyDao.get(entity);
    }

    @Override
    public Integer delete(Reply entity) {
        return replyDao.delete(entity);
    }

    @Override
    public Integer update(Reply entity) {
        return replyDao.update(entity);
    }
}
