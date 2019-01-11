package com.sitech.billing.customization.table.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询实体类
 *
 * @author sunzhen
 * @date 2019/1/8 14:06
 */
@Getter
@Setter
@ToString
public class Searcher {
    /* 是否支持查询*/
    private Boolean searchable;
    /* 查询顺位 */
    private Integer searchOrder;
    /* 查询方式 查看：com.sitech.billing.customization.table.type.OperatorType*/
    private Integer searchType;
    /* 是否为必选条件 */
    private Boolean required;
}
