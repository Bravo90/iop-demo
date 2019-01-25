package com.sitech.billing.datainput.dao;

import com.sitech.billing.datainput.model.DataInputTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sunzhen
 * @date 2019/1/24 14:12
 */
@Mapper
public interface DataInputTableMapper {

    List<DataInputTable> listTables(@Param("tableName") String tableName, @Param("tableDesc") String tableDesc);

    DataInputTable getTableById(@Param("id") int id);

    int saveTable(@Param("table") DataInputTable table);
}
