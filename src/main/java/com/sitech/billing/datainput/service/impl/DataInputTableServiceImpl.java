package com.sitech.billing.datainput.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class DataInputTableServiceImpl implements DataInputTableService {

    @Autowired
    private DataInputTableMapper dataInputTableMapper;

    @Override
    public PageInfo<DataInputTable> listTables(String tableName, String tableDesc, Integer tableId, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<DataInputTable> tables = dataInputTableMapper.listTables(tableName, tableDesc, tableId);
        PageInfo pageInfo = new PageInfo(tables);
        return pageInfo;
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
        if (ids.size() > 0) {
            return dataInputTableMapper.delTables(ids);
        }
        return 0;
    }

    @Override
    public int updateTable(DataInputTable table) {
        return dataInputTableMapper.updateTable(table);
    }
}
