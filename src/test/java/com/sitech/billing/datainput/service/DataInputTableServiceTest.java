package com.sitech.billing.datainput.service;

import com.alibaba.fastjson.JSON;
import com.sitech.billing.MainApplication;
import com.sitech.billing.datainput.model.DataInputTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
        List<DataInputTable> tables = dataInputTableService.listTables(null, null);
        System.out.println(JSON.toJSON(tables));
    }

    @Test
    public void getTableById() {
        System.err.println(dataInputTableService.getTableById(1));
    }

    @Test
    public void saveTable() {
    }

    @Test
    public void delTables() {
    }

    @Test
    public void updateTable() {
    }
}