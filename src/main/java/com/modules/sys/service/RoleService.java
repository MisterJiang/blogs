package com.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.modules.sys.mapper.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/11.
 */
public interface RoleService {

    PageInfo<Role> findPageList(Role entity, Integer page,Integer limit);

    List<Role> findAll(Role entity);

    Integer insert(Role entity);

    Role get(Role entity);

    Role get(String id);

    Integer delete(Role entity);

    Integer delete(Map<String, Object> map);
}
