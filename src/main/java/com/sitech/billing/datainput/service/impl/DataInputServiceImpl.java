package com.sitech.billing.datainput.service.impl;


import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.datainput.dao.DataInputMapper;
import com.sitech.billing.datainput.db.DbType;
import com.sitech.billing.datainput.db.sql.SqlBuilder;
import com.sitech.billing.datainput.model.DataInputTable;
import com.sitech.billing.datainput.service.DataInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunzhen
 * @date 2019/1/24 13:42
 */
@Service("dataInputService")
public class DataInputServiceImpl implements DataInputService {

    private static final int DEFAULT_BATCH_SIZE = 200;
    private static final  String FIELDS_SPLIT_REGEX = ",";

    @Autowired
    private DataInputMapper dataInputMapper;

    @Override
    @Transactional
    public int batchSave(List<String> list, DataInputTable table) {
        int rows = 0;
        int total = list.size();
        if (total == 0) {
            return rows;
        } else {
            String splitRegex = table.getSplitRegex();

            List<Map<String, String>> dataMapList = new ArrayList<>();
            String fields = table.getTableFields();
            String[] fieldArr = fields.split(FIELDS_SPLIT_REGEX);

            for (int i = 0; i < list.size(); i++) {
                if (dataMapList.size() == DEFAULT_BATCH_SIZE) {
                    //执行插入
                    rows += executeBatchInsert(table, dataMapList);
                    dataMapList.clear();
                    i = i - 1;
                } else {
                    Map<String, String> dataMap = new HashMap<>();
                    String data = list.get(i);
                    if (data.split(splitRegex).length == fieldArr.length) {
                        String[] dataArr = data.split(splitRegex);
                        for (int j = 0; j < dataArr.length; j++) {
                            dataMap.put(fieldArr[j], dataArr[j]);
                        }
                        dataMapList.add(dataMap);
                    } else {
                        throw new IopException(ErrorMsgEnum.FIELD_NOT_MATCH_DATA);
                    }
                }
            }
            if (dataMapList.size() > 0) {
                rows += executeBatchInsert(table, dataMapList);
            }
            return rows;
        }
    }

    /**
     * 根据数据库类型执行
     **/
    private int executeBatchInsert(DataInputTable table, List<Map<String, String>> dataMapList) {
        int rows = 0;
        switch (table.getDbType()) {
            case DbType.MYSQL:
                Map<String, String> map = SqlBuilder.buildMysql(table);
                rows = dataInputMapper.mysqlBatchInsert(map.get("insertSql"), map.get("valuesSql"), dataMapList);
                break;
            case DbType.ORACLE:
                rows = dataInputMapper.oracleBatchInsert("", dataMapList);
                break;
            case DbType.DMDB:
            default:
                break;
        }
        return rows;
    }
}
