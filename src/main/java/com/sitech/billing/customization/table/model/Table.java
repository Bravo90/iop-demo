package com.sitech.billing.customization.table.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据表实体类
 *
 * @author sunzhen
 * @date 2019/1/8 14:00
 */
@Getter
@Setter
@ToString
public class Table {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private String tableName;
    private String tableDesc;
    private boolean pageable;

    private List<Field> Fields = new ArrayList<>();
}
