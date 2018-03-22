package com.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.blogs.dao.ArticleDao;
import com.modules.sys.dao.UserDao;
import com.modules.sys.mapper.User;
import com.modules.sys.service.UserService;
import com.utils.DateUtils;
import com.utils.MD5Utils;
import com.utils.UUID_Utils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> findPageList(User entity, Integer page, Integer limit){
        PageHelper.startPage(page, limit, true);
        List<User> pageList = userDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public Integer insert(User entity) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = entity.getPassword();//密码原值
        Object salt = null;//盐值
        int hashIterations = 1024;//加密1024次
        Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        entity.setRoleType("normal");  //默认普通用户
        entity.setPassword(result.toString());
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser(entity.getUserName());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        return userDao.insert(entity);
    }

    @Override
    public User get(User entity) {
        return userDao.get(entity);
    }

    @Override
    public User get(String id) {
        return null;
    }
}
