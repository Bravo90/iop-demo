package com.sitech.billing.common.bean;

import com.sitech.billing.common.enums.ErrorMsgEnum;

/**
 * 封装Controller返回对象
 *
 * @author sunzhen
 * @date 2018-11-30 12:50:00
 */
public class JsonResult {

    private static final int OK = 1;
    private static final int FAIL = 0;
    private static final int DEFAULT_CODE = 0;
    //请求是否成功，1：成功；0：失败
    private int success;
    //返回码，默认0
    private int code;
    //提示信息
    private String message;
    //数据
    private Object data;


    /**
     * 请求成功，无返回数据和提示信息
     *
     * @return
     */

    public static JsonResult success(){
        return success(null);
    }

    /**
     * 请求成功，无返回数据，仅有提示信息
     *
     * @param message 提示信息
     * @return
     */
    public static JsonResult success(String message) {
        return success(message, null);
    }

    /**
     * 请求成功，无提示信息，仅有返回数据
     *
     * @param data 返回数据
     * @return
     */
    public static JsonResult success(Object data) {
        return success("", data);
    }

    /**
     * 请求成功，带有提示信息和返回数据
     *
     * @param message 提示信息
     * @param data    返回数据
     * @return
     */
    public static JsonResult success(String message, Object data) {
        return success(DEFAULT_CODE, message, data);
    }

    /**
     * 请求成功，带有返回码、提示信息和返回数据
     *
     * @param code    返回码
     * @param message 提示信息
     * @param data    返回数据
     * @return
     */
    public static JsonResult success(int code, String message, Object data) {
        return JsonResult.newInstance(OK, code, message, data);
    }

    /**
     * 请求失败，返回失败信息
     *
     * @param errorMsgEnum 系统自定义错误信息
     * @return
     * @see ErrorMsgEnum
     */

    public static JsonResult error(ErrorMsgEnum errorMsgEnum) {
        return error(errorMsgEnum.getErrorCode(), errorMsgEnum.getErrorMsg());
    }

    /**
     * 请求失败，返回失败信息
     *
     * @param message 失败信息
     * @return
     */
    public static JsonResult error(String message) {
        return error(DEFAULT_CODE, message);
    }

    /**
     * 请求失败，返回失败信息和返回码
     *
     * @param code    返回码
     * @param message 失败信息
     * @return
     */
    public static JsonResult error(int code, String message) {
        return JsonResult.newInstance(FAIL, code, message, null);
    }


    private JsonResult(int success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private static JsonResult newInstance(int success, int code, String message, Object data) {
        return new JsonResult(success, code, message, data);
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
