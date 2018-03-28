package com.modules.blogs.mapper;

import com.utils.EntityBase;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JIANGYINCAI
 * Date: 2018/3/23
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 * Description:评论
 */
public class Comment extends EntityBase {

    private String articleId;

    private String userName;

    private String replyMsg;

    private Integer zanCount;

    private List<Reply> replyList;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public Integer getZanCount() {
        return zanCount;
    }

    public void setZanCount(Integer zanCount) {
        this.zanCount = zanCount;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
