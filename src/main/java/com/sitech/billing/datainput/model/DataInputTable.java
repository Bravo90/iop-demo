package com.sitech.billing.datainput.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据导入：保存导入表信息的实体类
 *
 * @author sunzhen
 * @date 2019/1/24 13:19
 */
@Getter
@Setter
@ToString
public class DataInputTable {
    private int inputTableId;
    private String tableName;
    private String tableDesc;
    private String tableFields;
    private int dbType;
    private int userId;
}
