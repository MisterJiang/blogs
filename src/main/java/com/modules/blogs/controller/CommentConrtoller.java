package com.modules.blogs.controller;

import com.github.pagehelper.PageInfo;
import com.modules.blogs.mapper.Comment;
import com.modules.blogs.mapper.Reply;
import com.modules.blogs.service.ArticleService;
import com.modules.blogs.service.CommentService;
import com.modules.blogs.service.ReplyService;
import com.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @Autowired
    private ReplyService replyService;

    //显示评论列表
    @RequestMapping(value = "list")
    @ResponseBody
    public Result list(String articleId,
                       @RequestParam(value = "page", required=false, defaultValue = "1") Integer page,  //页码
                       @RequestParam(value = "limit", required=false, defaultValue = "5") Integer limit,  //每页显示数量
                       HttpServletRequest request, HttpServletResponse response){
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        PageInfo<Comment> pageList = commentService.findPageList(comment, page, 0);
        List<Comment> commentList = pageList.getList();
        Reply reply = new Reply();
        if (commentList.size() > 0){
            for (Comment c: commentList) {
                String cId = c.getId();
                reply.setCommentId(cId);
                PageInfo<Reply> replyPageList = replyService.findPageList(reply, 1, 0);
                if (replyPageList.getList().size() > 0){
                    c.setReplyList(replyPageList.getList());
                }
            }
        }
        return Result.successResult().setObj(pageList);
    }




    //评论保存
    @RequestMapping(value = "save")
    @ResponseBody
    public Result save(@RequestParam(value = "id", required = true) String id,
                       @RequestParam(value = "content", required = true) String content,
                       HttpServletRequest request, HttpServletResponse response){

        Comment comment = new Comment();
        comment.setArticleId(id);
        comment.setReplyMsg(content);
        commentService.insert(comment);
        return Result.successResult().setObj(comment);
    }


    //评论保存
    @RequestMapping(value = "replySave")
    @ResponseBody
    public Result replySave(@RequestParam(value = "commentId", required = true) String commentId,
                       @RequestParam(value = "toUserName", required = true) String toUserName,
                            @RequestParam(value = "replyMsg", required = true) String replyMsg,
                       HttpServletRequest request, HttpServletResponse response){
        Reply reply = new Reply();
        reply.setCommentId(commentId);
        reply.setReplyMsg(replyMsg);
        reply.setToUserName(toUserName);
        reply.setFromUserName((String) SecurityUtils.getSubject().getPrincipal());
        replyService.insert(reply);
        return Result.successResult().setObj(reply);
    }

}
