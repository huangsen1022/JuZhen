package hua.model;

import java.io.Serializable;

/**
 * Created by zhenghao on 2016/9/5.
 * 接口返回json
 */
public class JsonReturn implements Serializable {

    private int code;

    private String msg;

    private Object data;


    public JsonReturn(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public JsonReturn(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
