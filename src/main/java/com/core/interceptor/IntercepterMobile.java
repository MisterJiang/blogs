package com.core.interceptor;

import com.utils.JudgeIsMoblie;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/3/30
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class IntercepterMobile extends HandlerInterceptorAdapter {

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


       // super.afterCompletion(request, response, handler, ex);
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


       // super.postHandle(request, response, handler, modelAndView);


    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean judgeIsMoblie = JudgeIsMoblie.JudgeIsMoblie(request);
        if(judgeIsMoblie==true){
            response.sendRedirect("/web");
        }else{
            return true;
        }
        return true;
    }
}
