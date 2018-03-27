package com.modules.blogs.mapper;

import com.utils.EntityBase;

/**
 * Created with IntelliJ IDEA.
 * User: JIANGYINCAI
 * Date: 2018/3/23
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 * Description: 回复 评论
 */
public class Reply extends EntityBase {

    private String commentId;

    private String fromUserId;

    private String toUserId;

    private String replyMsg;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }
}
