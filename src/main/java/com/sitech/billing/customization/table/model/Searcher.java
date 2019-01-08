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
    private Integer orderNo;
    private boolean searchable;
    private String searchType;
}
