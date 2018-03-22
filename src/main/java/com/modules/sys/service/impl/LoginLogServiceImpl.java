package com.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.sys.dao.LoginLogDao;
import com.modules.sys.dao.UserDao;
import com.modules.sys.mapper.LoginLog;
import com.modules.sys.service.LoginLogService;
import com.utils.DateUtils;
import com.utils.UUID_Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public PageInfo<LoginLog> findPageList(LoginLog entity, Integer page, Integer limit) {
        PageHelper.startPage(page, limit, true);
        List<LoginLog> pageList = loginLogDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public Integer insert(LoginLog entity) {
        entity.setId(UUID_Utils.getUUID());
        entity.setCreateUser((String) SecurityUtils.getSubject().getPrincipal());
        entity.setCreateTime(DateUtils.parseDate(DateUtils.getDateTime()));
        return loginLogDao.insert(entity);
    }

    @Override
    public LoginLog get(LoginLog entity) {
        return loginLogDao.get(entity);
    }

    @Override
    public Integer delete(LoginLog entity) {
        return loginLogDao.delete(entity);
    }

    @Override
    public Integer update(LoginLog entity) {
        return loginLogDao.update(entity);
    }
}
