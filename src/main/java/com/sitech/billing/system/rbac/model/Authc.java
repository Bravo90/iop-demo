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
    private String authcDesc;


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

    public String getAuthcDesc() {
        return authcDesc;
    }

    public void setAuthcDesc(String authcDesc) {
        this.authcDesc = authcDesc;
    }

    @Override
    public String toString() {
        return "Authc{" +
                "authcId=" + authcId +
                ", authcName='" + authcName + '\'' +
                ", authcDesc='" + authcDesc + '\'' +
                '}';
    }
}
