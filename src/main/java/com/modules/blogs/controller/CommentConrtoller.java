package com.modules.blogs.controller;

import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Comment;
import com.modules.blogs.service.ArticleService;
import com.modules.blogs.service.CommentService;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: JiangYinCai
 * Date: 2018/3/26
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Controller
@RequestMapping(value = {"comment"})
public class CommentConrtoller {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    //显示评论列表
    @RequestMapping(value = "list")
    @ResponseBody
    public Result list(String articleId,
                       @RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                       @RequestParam(value = "limit", required=false, defaultValue = "5") Integer limit,  //每页显示数量
                       HttpServletRequest request, HttpServletResponse response){
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        PageInfo<Comment> pageList = commentService.findPageList(comment, page, limit);
        if (pageList.getList().size() > 0){

        }else {

        }
        return Result.successResult().setObj(pageList);
    }




    //评论保存
    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(@RequestParam(value = "type", required = false , defaultValue = "comment") String type,
                       HttpServletRequest request, HttpServletResponse response){
      //  commentService.insert()

        return Result.successResult();
    }




}
