package com.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/2/26.
 */
public class isAjaxRequest {

    public static boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;
        return isAjax;
    }
}
