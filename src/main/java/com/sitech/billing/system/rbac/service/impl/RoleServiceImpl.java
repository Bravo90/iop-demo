package com.sitech.billing.system.rbac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.system.rbac.dao.RoleMapper;
import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.User;
import com.sitech.billing.system.rbac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRoles() {
        return roleMapper.listRoles();
    }

    @Override
    public PageInfo<Role> listAllRoles(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.listAllRole();
        return new PageInfo<>(list);
    }

    @Override
    public Integer saveRole(Role role) {
        return roleMapper.saveRole(role);
    }

    @Override
    @Transactional
    public Integer delRoleByRoleId(Integer roleId) {
        int rows = roleMapper.delRoleByRoleId(roleId);
        //删除关联表 iop_sys_user_role
        roleMapper.deleteUserRolesByRoleId(roleId);
        //删除关联表 iop_sys_role_authc
        roleMapper.deleteRoleAuthcByRoleId(roleId);
        return rows;
    }

    @Override
    public Integer updateRole(Role role) {
        if (role.getRoleId() != null) {
            return roleMapper.updateRole(role);
        } else {
            throw new IopException(ErrorMsgEnum.ROLE_ID_IS_NULL);
        }
    }

    @Override
    public Role getRoleByRoleId(Integer roleId) {
        return roleMapper.getRoleByRoleId(roleId);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleMapper.getRoleByRoleName(roleName);
    }

    @Override
    public List<Role> listRoleByUser(User user) {
        if (user.getUserId() != null) {
            return roleMapper.listRoleByUser(user);
        } else {
            return null;
        }
    }

    @Override
    public int deleteUserRolesByUserId(Integer userId) {
        return roleMapper.deleteUserRolesByUserId(userId);
    }

    @Transactional
    @Override
    public int saveUserRoles(User user, List<Role> roles) {
        roleMapper.deleteUserRolesByUserId(user.getUserId());
        int rows = 0;
        if (roles.size() > 0) {
            rows = roleMapper.saveUserRoles(user, roles);
        }
        return rows;
    }

    @Override
    @Transactional
    public Integer assignRoleAuthc(Integer roleId, List<Integer> add, List<Integer> del) {
        int row = 0;
        if (add.size() > 0) {
            row += roleMapper.batchAddRoleAuthc(roleId, add);
        }
        if (del.size() > 0) {
            row += roleMapper.batchDelRoleAuthc(roleId, del);
        }
        return row;
    }
}
