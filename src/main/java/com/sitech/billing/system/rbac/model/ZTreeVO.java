package com.sitech.billing.system.rbac.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于生成前端zTree的数据结构
 *
 * @author sunzhen
 * @date 2019-01-03 09:03:12
 */
@Getter
@Setter
@ToString
public class ZTreeVO {
    private Integer authcId;
    private String name;
    private List<ZTreeVO> children = new ArrayList<>();
    private Boolean checked = false;
}
