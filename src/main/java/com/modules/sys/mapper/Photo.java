package com.modules.sys.mapper;

import com.utils.EntityBase;

/**
 * 照片
 * Created by Administrator on 2018/3/9.
 */
public class Photo extends EntityBase {
    private String type;
    private String userName;
    private String imageName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
