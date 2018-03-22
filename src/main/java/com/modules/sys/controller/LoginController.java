package com.modules.sys.controller;

import com.modules.sys.mapper.LoginLog;
import com.modules.sys.mapper.User;
import com.modules.sys.service.LoginLogService;
import com.modules.sys.service.UserService;
import com.utils.DateUtils;
import com.utils.Result;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2018/1/23.
 */
@Controller
@RequestMapping(value = {"login"})
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "")
    public String login(Model uiModel, HttpServletRequest request, HttpServletResponse response){
        uiModel.addAttribute("navigation", "login");
        return "modules/sys/login";
    }

    //登录
    @RequestMapping(value = "login")
    @ResponseBody
    public Result loginInfo(String userName, String password, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
       // response.setContentType("text/xml;charset=utf-8");
        // 获取subject对象
        Subject subject = SecurityUtils.getSubject();
        SavedRequest savedRequest = null;
        if (subject.isAuthenticated() == false){
            // 实例化用户名密码令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            token.setRememberMe(false);
            try {
                subject.login(token);
                User user = new User();
                user.setUserName(userName);
                subject.getSession().setAttribute("sessionInfo", userService.get(user));

                //获取ip地址
                String ip = request.getHeader("x-forwarded-for");
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }

                //获取浏览器信息
                // UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
               // OperatingSystem operatingSystem = userAgent.getOperatingSystem();

                LoginLog loginLog = new LoginLog();
                loginLog.setBrowserInfo(request.getHeader("User-Agent"));
                loginLog.setUserName(userName);
                loginLog.setLoginTime(DateUtils.getDateTime());
                loginLog.setIp(ip);
                loginLogService.insert(loginLog);
            }catch(ExcessiveAttemptsException e){
                return Result.errorResult().setMsg("登录失败多次!");
            }catch (IncorrectCredentialsException e){
                return Result.errorResult().setMsg("密码错误!");
            }catch (UnknownAccountException e){
                return Result.errorResult().setMsg("账号不存在!");
            }
        }
        savedRequest = (SavedRequest) subject.getSession(false).getAttribute(WebUtils.SAVED_REQUEST_KEY);
        Result result = Result.successResult();
        result.setMsg("登录成功！");
        if(savedRequest != null){
            result.setObj(savedRequest.getRequestURI());
        }
        PrintWriter pw= response.getWriter();
        System.out.println(pw);
        return result;
    }


    @RequestMapping(value = "reg")
    public String reg(Model uiModel, HttpServletRequest request, HttpServletResponse response){
        uiModel.addAttribute("navigation", "reg");
        return "modules/sys/reg";
    }

    //注册
    @RequestMapping(value = "regSave")
    @ResponseBody
    public Result regSave(String userName, String password, String email, String name,
                      HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isNotBlank(userName)){
            User user = new User();
            user.setUserName(userName);
            User u = userService.get(user);
            if (u != null){  //说明用户名已存在
                return Result.warnResult().setMsg("用户名已存在!");
            }else {
                user.setEmail(email);
                user.setPassword(password);
                user.setName(name);
                userService.insert(user);
                return Result.successResult().setMsg("注册成功!");
            }
        }else {
            return Result.errorResult().setMsg("注册失败, 请联系管理员！");
        }
    }
}
