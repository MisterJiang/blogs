package com.modules.sys.dao;

import com.common.CrudDao;
import com.modules.sys.mapper.Role;

import java.util.Map;


/**
 * Created by Administrator on 2018/2/11.
 */
public interface RoleDao extends CrudDao<Role> {

    Integer delete(Map<String, Object> map);
}
