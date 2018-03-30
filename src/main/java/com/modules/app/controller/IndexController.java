package com.modules.app.controller;

import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Article;
import com.modules.blogs.service.ArticleService;
import com.modules.sys.service.TimeLineService;
import com.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"web"})
public class IndexController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private TimeLineService timeLineService;
    //webapp
    @RequestMapping(value = "")
    public String app(Model uiModel){
        Article article = new Article();
        PageInfo<Article> pageList = articleService.findPageList(article, 1, 10);
        uiModel.addAttribute("pageList", pageList);
        uiModel.addAttribute("navigation", "index");
        uiModel.addAttribute("pageList", pageList);
        return "modules/web/index";
    }




}
