package com.sitech.billing.common.utils;

import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;

import java.util.Collection;
import java.util.Map;

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

    static void isEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            throw new IopException(ErrorMsgEnum.COLLECTION_EMPTY_ERROR);
        }
    }

    static void isEmpty(Map map) {
        if (map == null || map.size() == 0) {
            throw new IopException(ErrorMsgEnum.COLLECTION_EMPTY_ERROR);
        }
    }
}
