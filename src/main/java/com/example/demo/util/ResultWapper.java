package com.example.demo.util;


import java.io.Serializable;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/6
 */
public class ResultWapper implements Serializable {
    private static final long serialVersionUID = -5576279569985393220L;

    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    private String code;
    private String msg;
    private Object data;

    public static ResultWapper success(String msg, Object data) {
        ResultWapper resultWapper = new ResultWapper();
        resultWapper.setCode(SUCCESS);
        if (null != data)
            resultWapper.setData(data);
        if (null != msg && !"".equals(msg.trim()))
            resultWapper.setMsg(msg);
        return resultWapper;
    }

    public static ResultWapper success(Object data) {
        ResultWapper resultWapper = new ResultWapper();
        resultWapper.setCode(SUCCESS);
        if (null != data)
            resultWapper.setData(data);
        return resultWapper;
    }

    public static ResultWapper success(String msg) {
        ResultWapper resultWapper = new ResultWapper();
        resultWapper.setCode(SUCCESS);
        if (null != msg && !"".equals(msg.trim()))
            resultWapper.setMsg(msg);
        return resultWapper;
    }

    public static ResultWapper success() {
        ResultWapper resultWapper = new ResultWapper();
        resultWapper.setCode(SUCCESS);
        return resultWapper;
    }

    public static ResultWapper error(String msg) {
        ResultWapper resultWapper = new ResultWapper();
        resultWapper.setCode(ERROR);
        resultWapper.setMsg(msg);
        return resultWapper;
    }

    public ResultWapper() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
