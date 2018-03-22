package com.modules.blogs.service;

import com.modules.blogs.mapper.Article;
import com.github.pagehelper.PageInfo;

/**
 * Created by Administrator on 2018/1/19.
 */
public interface ArticleService {

    PageInfo<Article> findPageList(Article entity, Integer page,Integer limit);

    Integer insert(Article entity);

    Article get(Article entity);

    Integer delete(Article entity);

    Integer update(Article entity);

}
