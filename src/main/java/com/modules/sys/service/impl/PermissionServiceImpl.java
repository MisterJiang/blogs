package com.modules.sys.service.impl;

import com.github.pagehelper.PageInfo;
import com.modules.sys.dao.PermissionDao;
import com.modules.sys.dao.RoleDao;
import com.modules.sys.dao.UserDao;
import com.modules.sys.mapper.Permission;
import com.modules.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/2/11.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageInfo<Permission> findPageList(Permission entity) {
        return null;
    }

    @Override
    public Integer insert(Permission entity) {
        return permissionDao.insert(entity);
    }

    @Override
    public Permission get(Permission entity) {
        return permissionDao.get(entity);
    }

    @Override
    public Permission get(String id) {
        return permissionDao.get(id);
    }
}
