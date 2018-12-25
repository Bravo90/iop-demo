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

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;


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
    public Integer delRoleByRoleId(Integer roleId) {
        return roleMapper.delRoleByRoleId(roleId);
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
    public List<Role> listRoleByUser(User user) {
        if (user.getUserId() != null) {
            return roleMapper.listRoleByUser(user);
        } else {
            return null;
        }

    }
}
