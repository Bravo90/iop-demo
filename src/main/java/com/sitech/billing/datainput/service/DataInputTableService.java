package com.sitech.billing.datainput.service;

import com.sitech.billing.datainput.model.DataInputTable;

import java.util.List;

/**
 * @author sunzhen
 * @date 2019/1/24 14:17
 */
public interface DataInputTableService {

    List<DataInputTable> listTables(String tableName, String tableDesc);

    DataInputTable getTableById(int id);

    int saveTable(DataInputTable table);

    int delTables(List<Integer> ids);

    int updateTable(DataInputTable table);

}
