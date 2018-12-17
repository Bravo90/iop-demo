package com.sitech.billing.common.exception;

/**
 * 类描述(例:这是自定义系统异常)
 * @author sunzhen
 * @date 2018-11-30 11:40:21
 */
public class IopException extends RuntimeException{

    private String msg;

    public IopException(String msg){
        super(msg);
    }
}
