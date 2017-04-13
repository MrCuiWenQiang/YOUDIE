package com.my.fakerti.net.base;/**
 * Created by Administrator on 2017/4/1 0001.
 */

/**
 * created by Mr.C at 2017年4月1日 09:49:48
 **/
public class ApiResult {
    private static final long serialVersionUID = 1L;
    public static final int RESULT_OK = 200;
    public static final int TOKEN_INVAILL = 1500;
    private int statusCode;
    private String message;
    private String messageDetail;
    private String result;
    private int totalCount;
    private int code;
    private int total;
    private String info;
    private String data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


}
