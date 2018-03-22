package com.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.modules.sys.mapper.Permission;

/**
 * Created by Administrator on 2018/2/11.
 */
public interface PermissionService {
    PageInfo<Permission> findPageList(Permission entity);

    Integer insert(Permission entity);

    Permission get(Permission entity);

    Permission get(String id);
}
