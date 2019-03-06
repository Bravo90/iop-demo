package com.sitech.billing.customization.table.execute.impl;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.customization.table.execute.BaseExecute;
import com.sitech.billing.customization.table.model.request.UpdateAndInsertParam;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/15 15:19
 */
public class FileExecute extends BaseExecute {
    @Override
    public List<Map<String, Object>> query() {
        return null;
    }

    @Override
    public PageInfo<Map<String, Object>> queryByPage() {
        return null;
    }

    @Override
    public void insert(List<UpdateAndInsertParam> list) {
        throw new IopException(ErrorMsgEnum.OPERATION_NOT_SUPPORT);
    }

    @Override
    public void update(List<UpdateAndInsertParam> list) {
        throw new IopException(ErrorMsgEnum.OPERATION_NOT_SUPPORT);
    }

    @Override
    public void delete(List<List<UpdateAndInsertParam>> list) {
        throw new IopException(ErrorMsgEnum.OPERATION_NOT_SUPPORT);
    }
}
