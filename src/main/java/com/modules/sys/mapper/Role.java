package com.modules.sys.mapper;

/**
 * 角色表
 * Created by Administrator on 2018/2/11.
 */
public class Role{

    private String id;

    private String type;

    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
