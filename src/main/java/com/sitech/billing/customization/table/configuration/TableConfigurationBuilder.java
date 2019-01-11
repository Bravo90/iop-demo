package com.sitech.billing.customization.table.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sitech.billing.common.utils.AssertUtils;
import com.sitech.billing.customization.table.model.Button;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Searcher;
import com.sitech.billing.customization.table.model.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * TableConfiguration的建造类
 *
 * @author sunzhen
 * @date 2019/1/9 13:15
 */
public class TableConfigurationBuilder {

    public static TableConfiguration build(Integer id) {

        String configuration = getConfigurationById(id);
        JSONObject json = JSON.parseObject(configuration);
        TableConfiguration cfg = new TableConfiguration();
        AssertUtils.isNull(cfg);

        cfg.setViewId(json.getInteger("viewId"));
        cfg.setViewName(json.getString("viewName"));
        cfg.setEditable(json.getBoolean("editable"));
        cfg.setPageable(json.getBoolean("pageable"));
        cfg.setPageSize(json.getInteger("pageSize"));
        JSONArray jbtns = json.getJSONArray("button");

        List<Button> btns = new ArrayList<>();
        if (jbtns != null) {
            for (int i = 0; i < jbtns.size(); i++) {
                Button button = buildBtn(jbtns.getJSONObject(i));
                btns.add(button);
            }
        }
        cfg.setButtons(btns);
        JSONArray jtables = json.getJSONArray("table");
        AssertUtils.isNull(jtables);
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < jtables.size(); i++) {
            tables.add(buildTable(jtables.getJSONObject(i)));
        }

        cfg.setTables(tables);

        return cfg;
    }

    private static String getConfigurationById(Integer id) {
        return c2;
    }

    private static Button buildBtn(JSONObject json) {
        Button btn = new Button();
        btn.setBtnClass(json.getString("btnClass"));
        btn.setBtnName(json.getString("btnName"));
        return btn;
    }

    private static Table buildTable(JSONObject json) {
        AssertUtils.isNull(json);
        Table table = new Table();
        table.setTableName(json.getString("tableName"));
        table.setTableDesc(json.getString("tableDesc"));

        JSONArray jfields = json.getJSONArray("fields");
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < jfields.size(); i++) {
            fields.add(buildField(jfields.getJSONObject(i)));
        }
        table.setFields(fields);
        return table;
    }

    private static Field buildField(JSONObject json) {
        AssertUtils.isNull(json);
        Field field = new Field();

        field.setFieldName(json.getString("fieldName"));
        field.setFieldDesc(json.getString("fieldDesc"));
        field.setFieldAlias(json.getString("fieldAlias"));
        field.setFieldType(json.getInteger("fieldType"));
        field.setViewable(json.getBoolean("viewable"));
        field.setFieldOrder(json.getInteger("fieldOrder"));
        field.setSortable(json.getBoolean("sortable"));
        field.setKeyFiled(json.getBoolean("keyField"));

        field.setSearcher(buildSearcher(json.getJSONObject("search")));

        return field;
    }

    private static Searcher buildSearcher(JSONObject json) {
        Searcher searcher = new Searcher();
        searcher.setRequired(json.getBoolean("required"));
        searcher.setSearchable(json.getBoolean("searchable"));
        searcher.setSearchType(json.getInteger("searchType"));
        searcher.setSearchOrder(json.getInteger("searchOrder"));
        return searcher;
    }

    private static final String ccc = "{\"viewName\":\"用户信息查询\",\"viewId\":\"1\",\"editable\":false,\"pageable\":true,\"pageSize\":10,\"table\":[{\"tableName\":\"iop_sys_user\",\"tableDesc\":\"用户信息表\",\"fields\":[{\"fieldName\":\"user_id\",\"fieldDesc\":\"用户ID\",\"fieldAlias\":\"user_id\",\"fieldType\":1,\"viewable\":true,\"fieldOrder\":1,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":1,\"searchable\":true,\"searchType\":11,\"required\":false},\"fieldMapping\":[]},{\"fieldName\":\"username\",\"fieldDesc\":\"用户名称\",\"fieldAlias\":\"username\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":2,\"sortable\":false,\"keyField\":true,\"search\":{\"searchOrder\":2,\"searchable\":true,\"searchType\":1,\"required\":false},\"fieldMapping\":[]},{\"fieldName\":\"nickname\",\"fieldDesc\":\"用户昵称\",\"fieldAlias\":\"nickname\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":3,\"sortable\":false,\"keyField\":false,\"search\":{\"searchOrder\":3,\"searchable\":true,\"searchType\":1,\"required\":false},\"fieldMapping\":[]}]}],\"button\":[{\"btnName\":\"查询\",\"btnClass\":\"search\"},{\"btnName\":\"删除\",\"btnClass\":\"delete\"}]}";
    private static final String c1 = "{\"viewName\":\"用户信息查询\",\"viewId\":\"1\",\"editable\":false,\"pageable\":true,\"pageSize\":10,\"table\":[{\"tableName\":\"iop_sys_user\",\"tableDesc\":\"用户信息表\",\"fields\":[{\"fieldName\":\"user_id\",\"fieldDesc\":\"用户ID\",\"fieldAlias\":\"user_id\",\"fieldType\":1,\"viewable\":true,\"fieldOrder\":1,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":1,\"searchable\":true,\"searchType\":11,\"required\":false},\"fieldMapping\":[]},{\"fieldName\":\"username\",\"fieldDesc\":\"用户名称\",\"fieldAlias\":\"username\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":2,\"sortable\":false,\"keyField\":true,\"search\":{\"searchOrder\":2,\"searchable\":true,\"searchType\":1},\"fieldMapping\":[]},{\"fieldName\":\"nickname\",\"fieldDesc\":\"用户昵称\",\"fieldAlias\":\"nickname\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":3,\"sortable\":false,\"keyField\":false,\"search\":{\"searchOrder\":3,\"searchable\":true,\"searchType\":1,\"required\":false},\"fieldMapping\":[]}]}],\"button\":[{\"btnName\":\"查询\",\"btnClass\":\"search\"},{\"btnName\":\"删除\",\"btnClass\":\"delete\"}]}";
    private static final String c2 = "{\"viewName\":\"用户信息查询\",\"viewId\":\"1\",\"editable\":false,\"pageable\":true,\"pageSize\":10,\"table\":[{\"tableName\":\"iop_sys_user\",\"tableDesc\":\"用户信息表\",\"fields\":[{\"fieldName\":\"user_id\",\"fieldDesc\":\"用户ID\",\"fieldAlias\":\"user_id\",\"fieldType\":1,\"viewable\":true,\"fieldOrder\":1,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":1,\"searchable\":true,\"searchType\":11,\"required\":false},\"fieldMapping\":[]},{\"fieldName\":\"username\",\"fieldDesc\":\"用户名称\",\"fieldAlias\":\"username\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":2,\"sortable\":false,\"keyField\":true,\"search\":{\"searchOrder\":2,\"searchable\":true,\"searchType\":7},\"fieldMapping\":[]},{\"fieldName\":\"nickname\",\"fieldDesc\":\"用户昵称\",\"fieldAlias\":\"nickname\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":3,\"sortable\":false,\"keyField\":false,\"search\":{\"searchOrder\":3,\"searchable\":true,\"searchType\":1,\"required\":false},\"fieldMapping\":[]}]}],\"button\":[{\"btnName\":\"查询\",\"btnClass\":\"search\"},{\"btnName\":\"删除\",\"btnClass\":\"delete\"}]}";

}
