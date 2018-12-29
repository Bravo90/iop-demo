package com.sitech.billing.system.rbac.service;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.User;

import java.util.List;

public interface RoleService {

    List<Role> listRoles();

    PageInfo<Role> listAllRoles(Integer pageNum, Integer pageSize);

    Integer saveRole(Role role);

    Integer delRoleByRoleId(Integer roleId);

    Integer updateRole(Role role);

    Role getRoleByRoleId(Integer roleId);

    Role getRoleByRoleName(String roleName);

    /**
     * 根据用户获得用户的所有角色
     *
     * @param user 用户
     * @return
     */
    List<Role> listRoleByUser(User user);


    int deleteUserRolesByUserId(Integer userId);
    /**
     * 增加用户权限
     * @param user
     * @param roles
     * @return
     */
    int saveUserRoles(User user, List<Role> roles);

}
