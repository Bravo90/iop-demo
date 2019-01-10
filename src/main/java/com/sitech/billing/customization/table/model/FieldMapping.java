package com.sitech.billing.customization.table.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 映射实体类
 * @author sunzhen
 * @date 2019/1/8 17:13
 */
public class FieldMapping {
    private String key;
    private String value;
    private List<FieldMapping> children = new ArrayList<>();
}
