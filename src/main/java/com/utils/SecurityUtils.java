package com.utils;

import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2018/2/11.
 */
public class SecurityUtils {


    public static void sessionInfo(){
        Subject subject = org.apache.shiro.SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        Object sessionInfo = subject.getSession().getAttribute("sessionInfo");
        System.out.println(sessionInfo);
        System.out.println(principal);
        //  subject.getSession().setAttribute("");

    }







}
