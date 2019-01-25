package com.sitech.billing.datainput.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sitech.billing.MainApplication;
import com.sitech.billing.datainput.model.DataInputTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sunzhen
 * @date 2019/1/25 11:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class DataInputTableServiceTest {

    @Resource
    DataInputTableService dataInputTableService;

    @Test
    public void listTables() {
        PageInfo<DataInputTable> info = dataInputTableService.listTables(null, null, 10, 1);
        System.out.println(JSON.toJSON(info));
    }

    @Test
    public void getTableById() {
        System.err.println(dataInputTableService.getTableById(1));
    }

    @Test
    public void saveTable() {

        DataInputTable table = new DataInputTable();
        table.setDbType(1);
        table.setUserId(1);
        table.setTableFields("test_id,test_name,test_code,test_date,test_order,test_linkage");
        table.setTableName("iop_test_table");
        table.setTableDesc("测试用");
        int i = dataInputTableService.saveTable(table);
        System.err.println(i);
    }

    @Test
    public void delTables() {

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        System.err.println(dataInputTableService.delTables(ids));
    }

    @Test
    public void updateTable() {
        DataInputTable table = new DataInputTable();
        table.setInputTableId(2);
        table.setDbType(1);
        table.setUserId(1);
        table.setTableFields("test_id,test_name,test_code,test_date,test_order,test_linkage");
        table.setTableName("iop_test_table");
        table.setTableDesc("测试demo");
        System.err.println(dataInputTableService.updateTable(table));
    }
}