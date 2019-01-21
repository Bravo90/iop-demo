package com.sitech.billing.customization.table.configuration;

import com.sitech.billing.customization.table.model.Button;
import com.sitech.billing.customization.table.model.Col;
import com.sitech.billing.customization.table.model.FieldMapping;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 前端页面所需配置信息的实体类
 *
 * @author sunzhen
 * @date 2019/1/18 9:28
 */
@Getter
@Setter
@ToString
public class ViewConfiguration {
    private List<Col> cols = new ArrayList<>();
    private List<Col> searchCols = new ArrayList<>();
    private Map<String, List<FieldMapping>> fieldMap;
    private List<Button> btns = new ArrayList<>();
    private String viewName;
    private boolean editable;
    private boolean pageable;
    private int pageSize;
}
