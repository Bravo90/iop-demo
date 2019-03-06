package com.sitech.billing.customization.table.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 更新插入请求参数实体类
 *
 * @author sunzhen
 * @date 2019/3/6 14:32
 */
@Getter
@Setter
@ToString
public class UpdateAndInsertParam {

    private String fieldName;
    private String fieldValue;
    private String oldValue;
}
