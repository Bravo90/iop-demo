package com.sitech.billing.customization.table.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 请求实体类
 *
 * @author sunzhen
 * @date 2019/1/9 11:24
 */
@Getter
@Setter
@ToString
public class RequestPageInfo {
    private int pageSize = 10;
    private int pageNum = 1;
}
