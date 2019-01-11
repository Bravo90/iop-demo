package com.sitech.billing.common.utils;

import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;

/**
 * 判断的空
 *
 * @author sunzhen
 * @date 2019/1/11 13:19
 */
public interface AssertUtils {
    static void isNull(Object o) {
        if (o == null) {
            throw new IopException(ErrorMsgEnum.OBJECT_EMPTY_ERROR);
        }
    }
}
