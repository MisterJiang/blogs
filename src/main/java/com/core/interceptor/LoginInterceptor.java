package com.core.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/1/23.
 */
public class LoginInterceptor implements HandlerInterceptor{

    //执行Handler方法之前执行
    //用于身份认证、身份授权
    //比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
    //return false表示拦截，不向下执行
    //return true表示放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的url
        String url=request.getRequestURI();
        if(url.indexOf("/sys") >=0){
            //判断session
            HttpSession session=request.getSession();
            //从session中取出用户份信息
            String username=(String)session.getAttribute("username");
            if(username!=null){
                //身份存在，放行
                return true;
            }
            //执行这里表示用户身份需要验证，跳转到登录界面
            response.sendRedirect("/login?url=" + url);
         //   request.getRequestDispatcher("/login").forward(request, response);
        }
        return true;
    }

    //进入Handler方法之后，返回modelAndView之前执行
    //应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里
    //传到视图，也可以在这里统一指定视图
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }



    //执行Handler完成执行此方法
    //应用场景：统一异常处理，统一日志处理
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
