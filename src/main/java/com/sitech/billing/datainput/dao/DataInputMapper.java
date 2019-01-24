package com.sitech.billing.datainput.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sunzhen
 * @date 2019/1/24 14:12
 */
@Mapper
public interface DataInputMapper {

    int oracleBatchInsert(@Param("sql") String sql, @Param("list") List<Map<String, String>> list);

    int mysqlBatchInsert(@Param("insertSql") String insertSql,
                          @Param("valueSql") String valueSql,
                          @Param("list") List<Map<String, String>> list);
}
