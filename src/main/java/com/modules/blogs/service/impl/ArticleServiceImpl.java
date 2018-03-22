package com.modules.blogs.service.impl;

import com.modules.blogs.dao.ArticleDao;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.utils.DateUtils;
import com.utils.UUID_Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public PageInfo<Article> findPageList(Article entity, Integer page, Integer limit) {
        if (page == null){
            page = 1;
        }
        PageHelper.startPage(page, limit, true);
        List<Article> pageList = articleDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }


    @Override
    public Article get(Article entity) {
        return articleDao.get(entity);
    }



    @Override
    public Integer insert(Article entity) {
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser((String)SecurityUtils.getSubject().getPrincipal());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        entity.setView(0);  //默认点击次数为0
        return articleDao.insert(entity);
    }

    @Override
    public Integer delete(Article entity) {
        return articleDao.delete(entity);
    }

    @Override
    public Integer update(Article entity) {
        return articleDao.update(entity);
    }
}
