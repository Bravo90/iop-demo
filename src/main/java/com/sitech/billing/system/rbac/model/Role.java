package com.sitech.billing.system.rbac.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色实体
 *
 * @author sunzhen
 * @date 2018-12-15 21:46:12
 */
@Getter
@Setter
@ToString
public class Role {
    private Integer roleId;
    private String roleName;
    private String roleDesc;
}
