package com.sitech.billing.customization.table.handler.operator;


import com.sitech.billing.customization.table.handler.type.FieldTypeHandler;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.type.OperatorType;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理处理运算符
 *
 * @author sunzhen
 * @date 2019/1/10 10:32
 */
public class OperatorHandler {

    public static String handler(Field field, FieldValue fieldValue) {

        int searchType = field.getSearcher().getSearchType();
        int fieldType = field.getFieldType();
        List<String> values = fieldValue.getValue();
        String fieldName = field.getFieldAlias();

        StringBuffer sb = new StringBuffer();
        List<String> list = FieldTypeHandler.handler(fieldType, likeHandle(values, searchType));

        switch (searchType) {
            case OperatorType.EQUALS:
            case OperatorType.CODE_EQUALS:
                if (list.size() == 1) {
                    sb.append(fieldName).append("=").append(list.get(0));
                }
                break;
            case OperatorType.NOT_EQUALS:
                if (list.size() == 1) {
                    sb.append(fieldName).append("!=").append(list.get(0));
                }
                break;
            case OperatorType.MORE_THAN_EQUALS:
                if (list.size() == 1) {
                    sb.append(fieldName).append(">=").append(list.get(0));
                }
                break;
            case OperatorType.LESS_THAN_EQUALS:
                if (list.size() == 1) {
                    sb.append(fieldName).append("<=").append(list.get(0));
                }
                break;
            case OperatorType.MORE_THAN:
                if (list.size() == 1) {
                    sb.append(fieldName).append(">").append(list.get(0));
                }
                break;
            case OperatorType.LESS_THAN:
                if (list.size() == 1) {
                    sb.append(fieldName).append("<").append(list.get(0));
                }
                break;
            case OperatorType.LIKE_LEFT:
            case OperatorType.LIKE_RIGHT:
            case OperatorType.LIKE_ALL:
                if (list.size() == 1) {
                    sb.append(fieldName).append(" LIKE ").append(list.get(0));
                }
                break;
            case OperatorType.BETWEEN_AND:
                if (list.size() == 2) {
                    sb.append(fieldName).append(">=").append(list.get(0))
                            .append(" AND ").append(fieldName).append("<=").append(list.get(1));
                }
                break;
            case OperatorType.IN:
                sb.append(fieldName).append(" IN (");
                sb.append(String.join(",", list));
                sb.append(")");
                break;
        }
        return sb.toString();
    }

    private static List<String> likeHandle(List<String> values, int likeType) {

        List<String> list = new ArrayList<>();
        switch (likeType) {
            case OperatorType.LIKE_LEFT:

                for (String value : values) {
                    list.add("%" + value);
                }

                break;
            case OperatorType.LIKE_RIGHT:
                for (String value : values) {
                    list.add(value + "%");
                }
                break;
            case OperatorType.LIKE_ALL:
                for (String value : values) {
                    list.add("%" + value + "%");
                }
                break;
            default:
                list.addAll(values);
        }
        return list;
    }
}
