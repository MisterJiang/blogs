package com.core.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2018/2/11.
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // TODO Auto-generated method stub
        System.out.println("==============异常开始=============");
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
        if(ex instanceof UnauthorizedException){
            ModelAndView mv = new ModelAndView("unauthorized");
            return mv;
        }
        if (ex instanceof UnauthenticatedException){
            ModelAndView mv = new ModelAndView("unauthorized");
            return mv;
        }
        if (ex instanceof AuthorizationException){
            ModelAndView mv = new ModelAndView("unauthorized");
            return mv;
        }
       // ex.printStackTrace();
        System.out.println("==============异常结束=============");
        ModelAndView mv = new ModelAndView("error");
       // mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
        return mv;
    }
}
