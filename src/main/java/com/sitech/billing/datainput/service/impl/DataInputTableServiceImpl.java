package com.sitech.billing.datainput.service.impl;

import com.sitech.billing.datainput.dao.DataInputMapper;
import com.sitech.billing.datainput.dao.DataInputTableMapper;
import com.sitech.billing.datainput.model.DataInputTable;
import com.sitech.billing.datainput.service.DataInputTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunzhen
 * @date 2019/1/24 14:17
 */
@Service("dataInputTableService")
public class DataInputTableServiceImpl implements DataInputTableService{

    @Autowired
    private DataInputTableMapper dataInputTableMapper;

    @Override
    public List<DataInputTable> listTables(String tableName, String tableDesc) {

        return dataInputTableMapper.listTables(tableName,tableDesc);
    }

    @Override
    public DataInputTable getTableById(int id) {
        return dataInputTableMapper.getTableById(id);
    }

    @Override
    public int saveTable(DataInputTable table) {
        return dataInputTableMapper.saveTable(table);
    }

    @Override
    public int delTables(List<Integer> ids) {
        return 0;
    }

    @Override
    public int updateTable(DataInputTable table) {
        return 0;
    }
}
