package com.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.modules.sys.mapper.Photo;
import com.modules.sys.mapper.User;
import com.modules.sys.service.PhotoService;
import com.modules.sys.service.UserService;
import com.utils.DateUtils;
import com.utils.FileUploadUtil;
import com.utils.JsonMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片-相册
 * Created by Administrator on 2018/3/9.
 */
@Controller
@RequestMapping(value = {"photo"})
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    private static String property = System.getProperty("file.separator");
    @RequestMapping(value = "")
    public String user(HttpServletRequest request, HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");
        return "modules/sys/photoUpload";
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");
        String DirectoryName = "photofiles"  + property + user.getUserName();
        HashMap<String, Object> hashMap = Maps.newHashMap();
        HashMap<Object, Object> dataMap = Maps.newHashMap();
        String yearMonth = DateUtils.formatDate(new Date(), "yyyyMM");
        try{
            String imagePath = FileUploadUtil.uploadImage(request, response, DirectoryName);
            hashMap.put("code", 0);
            /*dataMap.put("src", request.getContextPath() + property + imagePath);
            hashMap.put("data", dataMap);*/
            String imageName = yearMonth + property + imagePath.substring(imagePath.lastIndexOf(property)+1, imagePath.length());
            //图片插入数据库中
            Photo photo = new Photo();
            photo.setUserName(user.getUserName());
            photo.setImageName(imageName);
            photo.setType("个人相册");
            photoService.insert(photo);
            String toJson = JsonMapper.getInstance().toJson(hashMap);
            return toJson;
        }catch (Exception e){
            hashMap.put("code", 0);
            hashMap.put("msg", "图片上传出错, 请联系管理员！");
            String toJson = JsonMapper.getInstance().toJson(hashMap);
            return toJson;
        }
    }


    @RequestMapping(value = "list")
    public String list(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                       @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                       Model uiModel, HttpServletRequest request, HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");
        Photo photo = new Photo();
        photo.setUserName(user.getUserName());
        PageInfo<Photo> pageList = photoService.findPageList(photo, page, limit);
        uiModel.addAttribute("pageList", pageList);
        return "modules/sys/photoList";
    }



    @RequestMapping(value = "listPage")
    @ResponseBody
    public String listPage(@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                       @RequestParam(value = "limit", required=false, defaultValue = "20") Integer limit,  //每页显示数量.
                       Model uiModel, HttpServletRequest request, HttpServletResponse response){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("sessionInfo");
        Photo photo = new Photo();
        photo.setUserName(user.getUserName());
        PageInfo<Photo> pageList = photoService.findPageList(photo, page, limit);
        Map<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("code", 0);
        hashMap.put("data", pageList.getList());
        hashMap.put("count", pageList.getTotal());
        String json = JsonMapper.getInstance().toJson(hashMap);
        return json;
    }

}
