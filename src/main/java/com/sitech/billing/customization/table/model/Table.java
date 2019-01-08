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

    /* 表ID */
    private Integer tableId;
    /* 表名称 如：sys_iop_user */
    private String tableName;
    /* 表描述 如：用户信息表*/
    private String tableDesc;
    /* 是否支持分页 */
    private boolean pageable;

    /* 表的字段集合 */
    private List<Field> Fields = new ArrayList<>();
    /* 功能按钮列表 */
    private List<Button> buttons = new ArrayList<>();

}
