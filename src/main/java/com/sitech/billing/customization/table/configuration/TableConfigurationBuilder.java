package com.sitech.billing.customization.table.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.common.utils.AssertUtils;
import com.sitech.billing.customization.table.model.Button;
import com.sitech.billing.customization.table.model.Field;
import com.sitech.billing.customization.table.model.Searcher;
import com.sitech.billing.customization.table.model.Table;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        cfg.setDataSourceType(json.getInteger("dataSourceType"));
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

    //TODO:需要处理，配置信息存储问题，存数据库直接存配置信息！
    private static String getConfigurationById(Integer id) {
        String cfg = cfgMap.get(id);
        if (cfg != null && !"".equals(cfg)) {
            return cfg;
        } else {
            throw new IopException(ErrorMsgEnum.CONFIGURATION_NOT_EXIST);
        }
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
        field.setMapJson(json.getJSONObject("jsonMapping"));
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

    private static Map<Integer, String> cfgMap = new HashMap();

    private static final String c1 = "";
    private static final String c2 = "{\"viewName\":\"测试信息查询\",\"viewId\":\"2\",\"dataSourceType\":1,\"editable\":false,\"pageable\":true,\"pageSize\":10,\"table\":[{\"tableName\":\"iop_test_table\",\"tableDesc\":\"测试信息表\",\"fields\":[{\"fieldName\":\"test_id\",\"fieldDesc\":\"测试ID\",\"fieldAlias\":\"test_id\",\"fieldType\":1,\"viewable\":true,\"fieldOrder\":1,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":1,\"searchable\":true,\"searchType\":1,\"required\":true},\"jsonMapping\":{}},{\"fieldName\":\"test_name\",\"fieldDesc\":\"测试名称\",\"fieldAlias\":\"test_name\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":2,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":2,\"searchable\":true,\"searchType\":7,\"required\":false},\"jsonMapping\":{}},{\"fieldName\":\"test_code\",\"fieldDesc\":\"测试编码\",\"fieldAlias\":\"test_code\",\"fieldType\":2,\"viewable\":true,\"fieldOrder\":3,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":3,\"searchable\":true,\"searchType\":1,\"required\":false},\"jsonMapping\":{\"type\":1,\"map\":[{\"0531\":\"济南\"},{\"0534\":\"德州\"}]}},{\"fieldName\":\"test_date\",\"fieldDesc\":\"测试时间\",\"fieldAlias\":\"test_date\",\"fieldType\":3,\"viewable\":true,\"fieldOrder\":4,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":4,\"searchable\":true,\"searchType\":10,\"required\":false},\"jsonMapping\":{}},{\"fieldName\":\"test_order\",\"fieldDesc\":\"测试排序\",\"fieldAlias\":\"test_order\",\"fieldType\":1,\"viewable\":true,\"fieldOrder\":5,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":5,\"searchable\":true,\"searchType\":5,\"required\":false},\"jsonMapping\":{}},{\"fieldName\":\"test_linkage\",\"fieldDesc\":\"测试联动\",\"fieldAlias\":\"test_linkage\",\"fieldType\":1,\"viewable\":true,\"fieldOrder\":6,\"sortable\":true,\"keyField\":true,\"search\":{\"searchOrder\":6,\"searchable\":true,\"searchType\":1,\"required\":false},\"jsonMapping\":{}}]}],\"button\":[{\"btnName\":\"查询\",\"btnClass\":\"search\"},{\"btnName\":\"删除\",\"btnClass\":\"delete\"}]}";

    static {
        cfgMap.put(1, c1);
        cfgMap.put(2, c2);
    }

}
