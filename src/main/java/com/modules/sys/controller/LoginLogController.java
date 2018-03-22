package com.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.modules.sys.mapper.LoginLog;
import com.modules.sys.mapper.User;
import com.modules.sys.service.LoginLogService;
import com.modules.sys.service.UserService;
import com.utils.JsonMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/9.
 */
@Controller
@RequestMapping(value = {"sys/loginLog"})
public class LoginLogController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "")
    public String loginLog(HttpServletRequest request, HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");
        return "modules/sys/loginLog";
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public String list(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                       @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                        HttpServletRequest request, HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");
        LoginLog loginLog = new LoginLog();
        PageInfo<LoginLog> pageList = loginLogService.findPageList(loginLog, page, limit);
        Map<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("code", 0);
        hashMap.put("data", pageList.getList());
        hashMap.put("count", pageList.getTotal());
        String json = JsonMapper.getInstance().toJson(hashMap);
        return json;
    }


}
