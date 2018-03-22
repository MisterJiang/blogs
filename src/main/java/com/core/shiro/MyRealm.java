package com.core.shiro;

import com.modules.sys.mapper.Role;
import com.modules.sys.mapper.User;
import com.modules.sys.service.PermissionService;
import com.modules.sys.service.RoleService;
import com.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/2/11.
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        SimpleAuthenticationInfo info = null;
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
 /*       System.out.println("验证当前Subject时获取到token为" +
                ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));*/
        User user = new User();
        user.setUserName(token.getUsername());
        user = userService.get(user);
        if (user != null){
            Object principal = token.getUsername();
            Object credentials = user.getPassword();
            String realmName = this.getName();
            info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        }else {
            throw new UnknownAccountException();
        }
        return info;
    }


    //验证权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Session session = SecurityUtils.getSubject().getSession();
        if (session.getAttribute("sessionInfo") == null){
            return info;
        }
        //获取当前登录的用户名
        String loginName = (String)super.getAvailablePrincipal(principals);
        //到数据库查是否有此对象
        User u = new User();
        u.setUserName(loginName);
        User user=userService.get(u);
        if (user!=null){
            Role role = new Role();
            Set<String> set= new HashSet<String>();
            if (user.getUserName().equals("superadmin")) {
                List<Role> roleList = roleService.findAll(role);
                for (Role r: roleList){
                    set.add(r.getType());
                }
            }else {
                //用户的角色集合
                role.setType(user.getRoleType());
                role = roleService.get(role);  //目前一人一个角色  多个权限
                set.add(role.getType());
            }
            info.setRoles(set);  //将角色放入info中
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
           /* List<Role> roleList=user.getRoleList();
            for (Role role : roleList) {
                info.addStringPermissions(role.getPermissionsName());
            }*/
            return info;
        }
        return info;
    }


    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "123456";//密码原值
        Object salt = null;//盐值
        int hashIterations = 1024;//加密1024次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        System.out.println(result);
    }

}
