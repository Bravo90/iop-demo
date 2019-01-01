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

    Authc getAuthcByAuthcName(String authcName);

    /**
     * 根据角色列表获得权限列表
     *
     * @param roleList
     * @return
     */
    List<Authc> listAuthcByRole(List<Role> roleList);

    /**
     * 获取父级节点集合
     *
     * @return
     */
    List<Authc> listParentAuthc();

    /**
     * 根据父级节点的ID获得子节点集合
     * @param parentId
     * @return
     */
    List<Authc> listAuthcByParentId(Integer parentId);
}
