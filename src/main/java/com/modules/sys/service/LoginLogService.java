package com.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Article;
import com.modules.sys.mapper.LoginLog;

/**
 * Created by Administrator on 2018/3/9.
 */
public interface LoginLogService {


    PageInfo<LoginLog> findPageList(LoginLog entity, Integer page,Integer limit);

    Integer insert(LoginLog entity);

    LoginLog get(LoginLog entity);

    Integer delete(LoginLog entity);

    Integer update(LoginLog entity);
}
