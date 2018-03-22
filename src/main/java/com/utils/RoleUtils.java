package com.utils;

import com.core.springContext.SpringContextHolder;
import com.modules.sys.dao.RoleDao;
import com.modules.sys.dao.UserDao;
import com.modules.sys.mapper.Role;
import com.modules.sys.mapper.User;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * Created by Administrator on 2018/2/11.
 */
public class RoleUtils {

    public static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
    public static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);

    //一个用户存在的角色
    public static Set<String> getRoleList(String id) {
        User user = userDao.get(id);
        Set<String> set= new HashSet<String>();
        if(user != null){
            Role role = roleDao.get(user.getRoleType());
            set.add(role.getRoleName());
            return set;
        }
        return null;
    }
}
