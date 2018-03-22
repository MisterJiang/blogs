package com.modules.blogs.utils;

import com.modules.blogs.dao.ArticleDao;
import com.modules.blogs.mapper.Article;
import com.core.springContext.SpringContextHolder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class TestUtils {
    public static ArticleDao articleDao = SpringContextHolder.getBean(ArticleDao.class);

    public static PageInfo<Article> findData(Article article){
        PageHelper.startPage(1, 20, true);
        List<Article> pageList = articleDao.findPageList(article);
        PageInfo page = new PageInfo(pageList);
        return page;
    }

}
