package com.modules.sys.mapper;

import com.utils.EntityBase;

/**
 * 登陆日志
 * Created by Administrator on 2018/3/9.
 */
public class LoginLog extends EntityBase {

    private String userName;

    private String ip;

    private String loginTime;

    private String browserInfo;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }
}
