package com.sitech.billing.customization.table.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * 字段实体类
 *
 * @author sunzhen
 * @date 2019/1/8 14:00
 */
@Getter
@Setter
@ToString
public class Field {

    /* 字段名称 如：id、user_name */
    private String fieldName;
    /* 字段转义 如："user_name"转义为"用户名称" */
    private String fieldDesc;
    /* 字段类型 查看：com.sitech.billing.customization.table.type.FieldType*/
    private Integer fieldType;
    /* 字段是否显示 */
    private Boolean viewable;
    /* 字段别名：sql语句中字段的别名*/
    private String fieldAlias;
    /* 字段顺位 */
    private Integer fieldOrder;
    /* 字段是否支持排序 */
    private Boolean sortable;
    /* 字段查询条件信息 */
    private Searcher searcher;
    /* 字段是否为主键 */
    private Boolean keyFiled;
    /* 字段映射（考虑多级联动的实现） */
    private JSONObject mapJson;

}
