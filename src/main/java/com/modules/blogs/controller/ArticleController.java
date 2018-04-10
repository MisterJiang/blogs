package com.modules.blogs.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.service.ArticleService;
import com.modules.sys.mapper.TimeLine;
import com.modules.sys.service.TimeLineService;
import com.modules.sys.service.UserService;
import com.qiniu.storage.model.DefaultPutRet;
import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */
@Controller
@RequestMapping(value = {"", "index"})
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private TimeLineService timeLineService;
    private static String property = System.getProperty("file.separator");
    //首页
    @RequestMapping(value = "")
    public String blogs(Model uiModel, Integer page, Integer pageSize, HttpServletRequest request, HttpServletResponse response){
        boolean judgeIsMoblie  = JudgeIsMoblie.JudgeIsMoblie(request);
        Article article = new Article();
        PageInfo<Article> pageList = articleService.findPageList(article, 1, 10);
        uiModel.addAttribute("pageList", pageList);
        uiModel.addAttribute("navigation", "index");
        if(judgeIsMoblie == true){
            return "modules/web/index";
        }else {
            return "modules/blogs/index";
        }
    }

    //文章列表
    @RequestMapping(value = "article/{type}/p{page}${urlSuffix}")
    public String article(/*@RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码*/
                          @RequestParam(value = "limit", required=false, defaultValue = "10") Integer limit,  //每页显示数量
                          @PathVariable String type,
                          @PathVariable Integer page,
                          Model uiModel){
     //   SecurityUtils.sessionInfo();
        Article article = new Article();
        PageInfo<Article> pageList = articleService.findPageList(article, page, limit);
        uiModel.addAttribute("pageList", pageList);
        uiModel.addAttribute("type", type);
        uiModel.addAttribute("navigation", "article");
        return "modules/blogs/articleList";
    }

    //文章详情
    @RequestMapping(value = "article/detail/{id}${urlSuffix}")
    public String detail(Model uiModel, @PathVariable String id){
        Article article = new Article();
        article.setId(id);
        article = articleService.get(article);
        articleService.update(article);  //更新点击数
        article.setView(article.getView() + 1);
        uiModel.addAttribute("article", article);
        uiModel.addAttribute("navigation", "article");
        return "modules/blogs/detail";
    }

    //文章发布
    /*@RequiresAuthentication*/ //用户已登录
    @RequestMapping(value = "article/publish")
    public String publish(Model uiModel){
        return "modules/blogs/publish_c";
    }

    //文章保存
    @RequestMapping(value = "article/publish/save")
    @ResponseBody
    public Result save(String content, String title, String textView){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setTextView(textView);
        articleService.insert(article);
        return Result.successResult().setObj(article.getId());
    }
    //文章图片上传
    @RequestMapping(value = "article/uploadImage")
    @ResponseBody
    public String uploadImage(HttpServletRequest request, HttpServletResponse response){
        String DirectoryName = "userfiles";
        HashMap<String, Object> hashMap = Maps.newHashMap();
        HashMap<Object, Object> dataMap = Maps.newHashMap();
        try{
            DefaultPutRet defaultPutRet = QiNiuUploadUtil.QinNiu2(request, "article");
            //  String imagePath = FileUploadUtil.uploadImage(request, response, DirectoryName);
            /**
             *
             {
             "code": 0 //0表示成功，其它失败
             ,"msg": "" //提示信息 //一般上传失败后返回
             ,"data": {
             "src": "图片路径"
             ,"title": "图片名称" //可选
             }
             }
             */
            hashMap.put("code", 0);
            //  http://otffjxbtr.bkt.clouddn.com/+图片名称
            dataMap.put("src", "http://otffjxbtr.bkt.clouddn.com/" + defaultPutRet.hash);
            hashMap.put("data", dataMap);
            String toJson = JsonMapper.getInstance().toJson(hashMap);
            return toJson;
        }catch (Exception e){
            hashMap.put("code", 1);
            hashMap.put("msg", "图片上传出错, 请联系管理员！");
            String toJson = JsonMapper.getInstance().toJson(hashMap);
            return toJson;
        }
    }


    //时间轴
    @RequestMapping(value = "time${urlSuffix}")
    public String time(Model uiModel, HttpServletRequest request, HttpServletResponse response){
        TimeLine timeLine = new TimeLine();
        List<TimeLine> timeLineList = timeLineService.findAllList(timeLine);
        uiModel.addAttribute("timeLineList", timeLineList);
        uiModel.addAttribute("navigation", "time");
        return "modules/blogs/time";
    }


    @RequestMapping(value = "liuyan${urlSuffix}")
    public String liuyan(Model uiModel, HttpServletRequest request, HttpServletResponse response){




        uiModel.addAttribute("navigation", "liuyan");

        return "modules/blogs/liuyan";
    }
}
