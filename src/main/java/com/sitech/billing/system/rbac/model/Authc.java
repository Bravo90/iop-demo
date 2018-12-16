package com.sitech.billing.system.rbac.model;

/**
 * 权限实体类
 *
 * @author sunzhen
 * @date 2018-12-15 21:40:23
 */
public class Authc {
    private Integer authcId;
    private String authcName;

    public Integer getAuthcId() {
        return authcId;
    }

    public void setAuthcId(Integer authcId) {
        this.authcId = authcId;
    }

    public String getAuthcName() {
        return authcName;
    }

    public void setAuthcName(String authcName) {
        this.authcName = authcName;
    }

    @Override
    public String toString() {
        return "Authc{" +
                "authcId=" + authcId +
                ", authcName='" + authcName + '\'' +
                '}';
    }
}
