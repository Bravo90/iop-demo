package com.sitech.billing.system.rbac.dao;

import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> listAllRole();

    Integer saveRole(@Param("role") Role role);

    Integer delRoleByRoleId(@Param("roleId") Integer roleId);

    Integer updateRole(@Param("role") Role role);

    Role getRoleByRoleId(@Param("roleId") Integer roleId);

    List<Role> listRoleByUser(@Param("user") User user);


}
