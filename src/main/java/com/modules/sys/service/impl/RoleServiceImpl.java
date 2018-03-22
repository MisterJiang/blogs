package com.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.modules.sys.dao.PermissionDao;
import com.modules.sys.dao.RoleDao;
import com.modules.sys.dao.UserDao;
import com.modules.sys.mapper.Role;
import com.modules.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/11.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageInfo<Role> findPageList(Role entity, Integer page,Integer limit) {
        PageHelper.startPage(page, limit, true);
        List<Role> pageList = roleDao.findPageList(entity);
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }

    @Override
    public List<Role> findAll(Role entity) {
        return roleDao.findPageList(entity);
    }

    @Override
    public Integer insert(Role entity) {
        return roleDao.insert(entity);
    }

    @Override
    public Role get(Role entity) {
        return roleDao.get(entity);
    }

    @Override
    public Role get(String id) {

        return roleDao.get(id);
    }

    @Override
    public Integer delete(Role entity) {

        return roleDao.delete(entity);
    }

    @Override
    public Integer delete(Map<String, Object> map) {


        return roleDao.delete(map);
    }
}
