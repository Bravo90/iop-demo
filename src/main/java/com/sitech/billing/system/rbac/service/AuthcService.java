package com.sitech.billing.system.rbac.service;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.system.rbac.model.Authc;
import com.sitech.billing.system.rbac.model.Role;

import java.util.List;

public interface AuthcService {

    PageInfo<Authc> listAllAuthc(Integer pageNum, Integer pageSize);

    Integer saveAuthc(Authc authc);

    Integer delAuthcByAuthcId(Integer authcId);

    Integer updateAuthc(Authc authc);

    Authc getAuthcByAuthcId(Integer authcId);

    /**
     * 根据角色列表获得权限列表
     *
     * @param roleList
     * @return
     */
    List<Authc> listAuthcByRole(List<Role> roleList);

}
