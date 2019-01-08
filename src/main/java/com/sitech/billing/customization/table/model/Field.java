package com.sitech.billing.customization.table.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字段实体类
 *
 * @author sunzhen
 * @date 2019/1/8 14:00
 */
@Getter
@Setter
@ToString
public class Field {

    private String fieldName;
    private String fieldDesc;
    private String aliasName;

    private boolean sortable;

    private Searcher searcher;



}
