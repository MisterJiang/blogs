package com.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.modules.sys.mapper.User;


/**
 * Created by Administrator on 2018/2/8.
 */
public interface UserService {

    PageInfo<User> findPageList(User entity, Integer page,Integer limit);

    Integer insert(User entity);

    User get(User entity);

    User get(String id);
}
