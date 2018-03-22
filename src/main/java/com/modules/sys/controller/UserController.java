package com.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.service.ArticleService;
import com.modules.sys.mapper.Role;
import com.modules.sys.mapper.TimeLine;
import com.modules.sys.mapper.User;
import com.modules.sys.service.RoleService;
import com.modules.sys.service.TimeLineService;
import com.modules.sys.service.UserService;
import com.utils.JsonMapper;
import com.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19.
 */
@Controller
@RequestMapping(value = {"sys"})
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TimeLineService timeLineService;

    //用户管理初始化  超级管理员权限
    @RequestMapping(value = "user")
    @RequiresRoles(value={"superadmin"}, logical= Logical.AND)
    public String user(HttpServletRequest request, HttpServletResponse response){
        return "modules/sys/user";
    }

    //角色管理初始化  超级管理员权限
    @RequestMapping(value = "role")
    @RequiresRoles(value={"superadmin"}, logical= Logical.AND)
    public String role(HttpServletRequest request, HttpServletResponse response){
        return "modules/sys/role";
    }

    //用户列表
    @RequestMapping(value = "userList")
    @ResponseBody
    public String userList(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                           @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                            HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        PageInfo<User> pageList = userService.findPageList(user, page, limit);
        Map<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("code", 0);
        hashMap.put("data", pageList.getList());
        hashMap.put("count", pageList.getTotal());
        String json = JsonMapper.getInstance().toJson(hashMap);
        return json;
    }

    //角色列表
    @RequestMapping(value = "roleList")
    @ResponseBody
    public String roleList(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                           @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                           HttpServletRequest request, HttpServletResponse response){
        Role role = new Role();
        PageInfo<Role> pageList = roleService.findPageList(role, page, limit);
        Map<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("code", 0);
        hashMap.put("data", pageList.getList());
        hashMap.put("count", pageList.getTotal());
        String json = JsonMapper.getInstance().toJson(hashMap);
        return json;
    }

    //个人用户信息
    @RequestMapping(value = "userinfo")
    public String userinfo(Model uiModel, HttpServletRequest request, HttpServletResponse response){
        String userName = (String)SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        user.setUserName(userName);
        user = userService.get(user);
        uiModel.addAttribute("user", user);
        return "modules/sys/userinfo";
    }

    //删除
    @RequestMapping("role/delete")
    @ResponseBody
    public Result roleDelete(@RequestParam(value = "id", required=true) String id){
        Role role = new Role();
        role.setId(id);
    //  Integer integer = roleService.delete(role);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        Integer delete = roleService.delete(map);
        return Result.successResult().setMsg("删除成功！");
    }

    //文章
    @RequestMapping(value = "article")
    public String article(Model uiModel, HttpServletRequest request, HttpServletResponse response){
        return "modules/sys/article";
    }

    //文章列表
    @RequestMapping(value = "articleList")
    @ResponseBody
    public String articleList(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                              @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量
                              HttpServletRequest request, HttpServletResponse response){
        Article article = new Article();
        PageInfo<Article> pageList = articleService.findPageList(article, page, limit);
        Map<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("code", 0);
        hashMap.put("data", pageList.getList());
        hashMap.put("count", pageList.getTotal());
        String json = JsonMapper.getInstance().toJson(hashMap);
        return json;
    }

    //文章
    @RequestMapping(value = "article/delete")
    @ResponseBody
    public Result articleDelete(@RequestParam(value = "id", required=true) String id){
        Article article = new Article();
        article.setId(id);
        /*HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);*/
        Integer delete = articleService.delete(article);
        return Result.successResult().setMsg("删除成功！");
    }

    //时间轴
    @RequestMapping(value = "time")
    public String time(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
            @RequestParam(value = "limit", required=false, defaultValue = "5") Integer limit,  //每页显示数量
            Model uiModel, HttpServletRequest request, HttpServletResponse response){
        TimeLine timeLine = new TimeLine();
        PageInfo<TimeLine> pageList = timeLineService.findPageList(timeLine, page, limit);
        uiModel.addAttribute("pageList", pageList);
        return "modules/sys/time";
    }

    //文章
    @RequestMapping(value = "timeLine/delete")
    @ResponseBody
    public Result timeLineDelete(@RequestParam(value = "id", required=true) String id){
        TimeLine timeLine = new TimeLine();
        timeLine.setId(id);
        Integer delete = timeLineService.delete(timeLine);
        return Result.successResult().setMsg("删除成功！");
    }


    //时间轴列表
    @RequestMapping(value = "timeLineList")
    @ResponseBody
    public String timeLineList(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                              @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量
                              HttpServletRequest request, HttpServletResponse response){
        TimeLine timeLine = new TimeLine();
        PageInfo<TimeLine> pageList = timeLineService.findPageList(timeLine, page, limit);
        Map<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("code", 0);
        hashMap.put("data", pageList.getList());
        hashMap.put("count", pageList.getTotal());
        String json = JsonMapper.getInstance().toJson(hashMap);
        return json;
    }


    //时间轴
    @RequestMapping(value = "saveTimeLine")
    @ResponseBody
    public Result saveTimeLine(String content, HttpServletRequest request, HttpServletResponse response){
        TimeLine timeLine = new TimeLine();
        timeLine.setContent(content);
        Integer insert = timeLineService.insert(timeLine);
        return Result.successResult();
    }



}
