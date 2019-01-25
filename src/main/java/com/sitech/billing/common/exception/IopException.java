package com.sitech.billing.common.exception;

import com.sitech.billing.common.enums.ErrorMsgEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义异常
 *
 * @author sunzhen
 * @date 2018-11-30 11:40:21
 */
@Slf4j
public class IopException extends RuntimeException {

    private Integer errorCode;

    public IopException(String e){
        super(e);
    }

    public IopException(ErrorMsgEnum errorMsg) {
        super(errorMsg.getErrorMsg());
        this.errorCode = errorMsg.getErrorCode();
    }
}
