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
    private boolean searchable;
    /* 查询顺位 */
    private Integer searchOrder;
    /* 查询方式 */
    private String searchType;
    /* 是否为必选条件 */
    private boolean required;
}
