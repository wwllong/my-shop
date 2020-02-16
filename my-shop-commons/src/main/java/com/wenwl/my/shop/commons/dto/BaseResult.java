package com.wenwl.my.shop.commons.dto;

import java.io.Serializable;

/**
 * @author wenwl
 * @className BaseResult
 * @dsecription 自定义返回结果
 * @data 2020/2/4
 * @vserion 1.0.0
 */
public class BaseResult implements Serializable {

    public static final int STATUS_SUCCESS = 200;

    public static final int STATUS_FAIL = 500;

    /**
     * 状态码
     */
    private int status;

    /**
     * 返回给客户端的信息
     */
    private String message;

    public static BaseResult success(){
        return BaseResult.createResult(STATUS_SUCCESS,"成功");
    }

    public static BaseResult success(String message){
        return BaseResult.createResult(STATUS_SUCCESS,message);
    }

    public static BaseResult fail(){
        return BaseResult.createResult(STATUS_FAIL,"失败");
    }

    public static BaseResult fail(String message){
        return BaseResult.createResult(STATUS_FAIL,message);
    }

    public int getStatus() {
        return status;
    } 

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static BaseResult createResult(int status, String message){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }
}
