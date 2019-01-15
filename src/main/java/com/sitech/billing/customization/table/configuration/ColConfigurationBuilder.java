package com.sitech.billing.customization.table.configuration;

import com.sitech.billing.customization.table.model.Col;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Table;

import java.util.*;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/15 16:50
 */
public class ColConfigurationBuilder {

    public static List<Col> builder(TableConfiguration tableConfiguration) {
        List<Col> cols = new ArrayList<>();
        List<Table> tables = tableConfiguration.getTables();
        for (Table table : tables) {
            List<Field> fields = table.getFields();
            for (Field field : fields) {
                Col col = new Col();
                col.setFieldName(field.getFieldName());
                col.setFieldDesc(field.getFieldDesc());
                col.setFieldType(field.getFieldType());
                col.setFieldOrder(field.getFieldOrder());
                col.setViewable(field.getViewable());
                col.setSortable(field.getSortable());
                col.setSearchable(field.getSearcher().getSearchable());
                col.setRequired(field.getSearcher().getRequired());
                col.setKeyFiled(field.getKeyFiled());
                col.setFieldMapping(field.getFieldMapping());
                cols.add(col);
            }
        }

        Collections.sort(cols, new Comparator<Col>() {
            @Override
            public int compare(Col o1, Col o2) {
                if (o1.getFieldOrder() > o2.getFieldOrder()) {
                    return 1;
                } else if (o1.getFieldOrder() < o2.getFieldOrder()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return cols;
    }
}
