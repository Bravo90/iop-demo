package com.sitech.billing.system.rbac.dao;

import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> listRoles();

    List<Role> listAllRole();

    Integer saveRole(@Param("role") Role role);

    Integer delRoleByRoleId(@Param("roleId") Integer roleId);

    Integer updateRole(@Param("role") Role role);

    Role getRoleByRoleId(@Param("roleId") Integer roleId);

    Role getRoleByRoleName(@Param("roleName") String roleName);

    List<Role> listRoleByUser(@Param("user") User user);

    int saveUserRoles(@Param("user") User user, @Param("roles") List<Role> roles);

    int deleteUserRolesByUserId(@Param("userId") Integer userId);

    int deleteUserRolesByRoleId(@Param("roleId") Integer roleId);

    void deleteRoleAuthcByRoleId(@Param("roleId") Integer roleId);

    Integer batchAddRoleAuthc(@Param("roleId")Integer roleId,@Param("list")List<Integer> list);

    Integer batchDelRoleAuthc(@Param("roleId")Integer roleId,@Param("list")List<Integer> list);
}
