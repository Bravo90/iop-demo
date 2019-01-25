package com.sitech.billing.datainput.db.sql;

import com.sitech.billing.datainput.model.DataInputTable;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/25 9:40
 */
public class SqlBuilder {

    private static final String SPLIT_REGEX = ",";

    public static Map<String, String> buildMysql(DataInputTable table) {

        String[] fields = table.getTableFields().split(SPLIT_REGEX);

        StringBuffer insertSql = new StringBuffer();
        StringBuffer valuesSql = new StringBuffer();
        insertSql.append("INSERT INTO ").append(table.getTableName()).append(" (");
        valuesSql.append("(");

        for (int i = 0; i < fields.length; i++) {
            if (i == fields.length - 1) {
                insertSql.append("`").append(fields[i]).append("`) VALUES ");
                valuesSql.append("#{item.").append(fields[i]).append("})");
            } else {
                insertSql.append("`").append(fields[i]).append("`,");
                valuesSql.append("#{item.").append(fields[i]).append("},");
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("insertSql", insertSql.toString());
        map.put("valuesSql", valuesSql.toString());

        return map;
    }

    String buildOracle(DataInputTable table) {
        return null;
    }
}
