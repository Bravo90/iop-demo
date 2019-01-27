package com.sitech.billing.datainput.service;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.datainput.model.DataInputTable;

import java.util.List;

/**
 * @author sunzhen
 * @date 2019/1/24 14:17
 */
public interface DataInputTableService {

    PageInfo<DataInputTable> listTables(String tableName, String tableDesc, Integer tableId, int pageSize, int pageNum);

    DataInputTable getTableById(int id);

    int saveTable(DataInputTable table);

    int delTables(List<Integer> ids);

    int updateTable(DataInputTable table);

}
