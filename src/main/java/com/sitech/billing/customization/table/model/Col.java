package com.sitech.billing.customization.table.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 字段配置类
 *
 * @author sunzhen
 * @date 2019/1/15 16:45
 * @see Field
 */
@Getter
@Setter
@ToString
public class Col {
    private String fieldName;
    private String fieldDesc;
    private Integer fieldType;
    private Integer fieldOrder;
    private Boolean viewable;
    private Boolean sortable;
    private Boolean searchable;
    private Boolean required;
    private Boolean keyFiled;
    private Integer seachOrder;
    private List<FieldMapping> fieldMapping;
}
