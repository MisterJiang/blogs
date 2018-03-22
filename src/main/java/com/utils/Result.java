package com.utils;

import com.utils.JsonMapper;

import java.io.Serializable;

public class Result implements Serializable {
    public static final int SUCCESS = 1;
    public static final int WARN = 2;
    public static final int ERROR = 0;
    public static final String SUCCESS_MSG = "操作成功！";
    public static final String WARN_MSG = "警告信息！";
    public static final String ERROR_MSG = "操作失败:发生未知异常！";
    private int code;
    private String msg;
    private Object obj;

    public Result() {
    }

    public Result(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public static Result successResult() {
        return new Result(1, "操作成功！", (Object)null);
    }

    public static Result warnResult() {
        return new Result(2, "警告信息！", (Object)null);
    }

    public static Result errorResult() {
        return new Result(0, "操作失败:发生未知异常！", (Object)null);
    }

    public int getCode() {
        return this.code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return this.obj;
    }

    public Result setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public String toString() {
        new JsonMapper();
        return JsonMapper.nonDefaultMapper().toJson(this);
    }
}