package com.sitech.billing.customization.table.configuration;

import com.sitech.billing.customization.table.model.Button;
import com.sitech.billing.customization.table.model.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * table的配置信息
 *
 * @author sunzhen
 * @date 2019/1/9 10:20
 */
@Getter
@Setter
@ToString
public class TableConfiguration {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private int viewId;
    private String viewName;
    private boolean editable;
    private boolean pageable;
    private int pageSize;

    List<Table> tables = new ArrayList<>();
    List<Button> buttons = new ArrayList<>();
}
