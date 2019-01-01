package com.sitech.billing.system.rbac.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限实体类
 *
 * @author sunzhen
 * @date 2018-12-15 21:40:23
 */
@Getter
@Setter
@ToString
public class Authc {
    private Integer authcId;
    private String authcName;
    private String authcDesc;
}
