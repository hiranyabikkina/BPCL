package com.bpcl.util;

import java.util.Date;

import com.bpcl.constant.Code;



public class ResponseBean<T> {

    /**
     * Return status code
     */
    protected int returnCode;

    /**
     * Return message
     */
    protected String msg;

    /**
     * Return data
     */
    protected T data;

    /**
     * Whether the return is successful (true: successful; false: failed)
     */
    protected boolean success;

    /**
     * Return time (Long timestamp)
     */
    protected long returnTime;

    public ResponseBean(int returnCode, String msg, T data, boolean success) {
        this.msg = msg;
        this.data = data;
        this.returnCode = returnCode;
        this.returnTime = (new Date()).getTime();
        this.success = success;
    }

    public static <T> ResponseBean <T> createSuccess(String msg) {
        return new ResponseBean<T>(Code.SUCCESS, msg, null, true);
    }

    public static <T> ResponseBean <T> createSuccess(String msg, T data) {
        return new ResponseBean<T>(Code.SUCCESS, msg, data, true);
    }

    public static <T> ResponseBean <T> createSuccess(int returnCode, String msg) {
        return new ResponseBean<T>(returnCode, msg, null, true);
    }

    public static <T> ResponseBean <T> createSuccess(int returnCode, String msg, T data) {
        return new ResponseBean<T>(returnCode, msg, data, true);
    }

    public static <T> ResponseBean <T> createError(String msg) {
        return new ResponseBean<T>(Code.ERROR_COMMON, msg, null, false);
    }

    public static <T> ResponseBean <T> createError(String msg, T data) {
        return new ResponseBean<T>(Code.ERROR_COMMON, msg, data, false);
    }

    public static <T> ResponseBean <T> createError(int returnCode, String msg) {
        return new ResponseBean<T>(returnCode, msg, null, false);
    }

    public static <T> ResponseBean <T> createError(int returnCode, String msg, T data) {
        return new ResponseBean<T>(returnCode, msg, data, false);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public long getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(long returnTime) {
        this.returnTime = returnTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
