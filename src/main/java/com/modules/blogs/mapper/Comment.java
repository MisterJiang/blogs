package com.modules.blogs.mapper;

import com.utils.EntityBase;

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

    private String userId;

    private String replyMsg;

    private Integer zanCount;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
