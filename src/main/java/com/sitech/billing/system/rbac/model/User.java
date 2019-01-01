package com.sitech.billing.system.rbac.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户实体类
 *
 * @author sunzhen
 * @date 2018-12-15 21:50:43
 */
@Getter
@Setter
@ToString
public class User {
    private Integer userId;
    private String username;
    private String nickname;
    private String password;
}
