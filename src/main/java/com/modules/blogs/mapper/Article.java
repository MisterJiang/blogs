package com.modules.blogs.mapper;

import com.utils.EntityBase;

import java.beans.Introspector;

/**
 * Created by Administrator on 2018/1/19.
 */
public class Article extends EntityBase{

    private String title;   //标题
    private String category; //类型
    private String content;  //内容
    private String keywords;  //关键字
    private Integer view;  //点击次数
    private String textView;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }
}
