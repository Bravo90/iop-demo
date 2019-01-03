package com.sitech.billing.system.rbac.service;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.MainApplication;
import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class RoleServiceTest {

    @Resource
    private RoleService roleService;

    @Test
    public void listAllRoles() {
        PageInfo<Role> rolePageInfo = roleService.listAllRoles(1, 10);
        System.out.println(rolePageInfo);
    }

    @Test
    public void saveRole() {
        Role role = new Role();
        role.setRoleName("超级管理员");
        role.setRoleDesc("拥有最多权限的账户");

        Integer integer = roleService.saveRole(role);
        System.out.println(integer);

    }

    @Test
    public void delRoleByRoleId() {

        System.out.println(roleService.delRoleByRoleId(2));
    }

    @Test
    public void updateRole() {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleDesc("拥有最多的权限，该角色属于最高级管理员");
        roleService.updateRole(role);
    }

    @Test
    public void getRoleByRoleId() {
        System.out.println(roleService.getRoleByRoleId(1));
    }

    @Test
    public void getRoleByRoleName() {
        System.out.println(roleService.getRoleByRoleName("普通用户"));
        System.out.println(roleService.getRoleByRoleName("123"));
    }

    @Test
    public void listRoleByUser() {
        User user = new User();
        user.setUserId(1);
        List<Role> roles = roleService.listRoleByUser(user);
        System.out.println(roles.toString());
    }


    @Test
    public void saveUserRoles() {

        User user = new User();
        user.setUserId(2);

        List<Role> roles = new ArrayList<>();
       /* Role r1 = new Role();r1.setRoleId(1);
        roles.add(r1);
        Role r2 = new Role();r2.setRoleId(2);
        roles.add(r2);*/

        roleService.saveUserRoles(user, roles);
    }

    @Test
    public void assignRoleAuthc() {

        List<Integer> addList = new ArrayList<>();
        addList.add(1002);
        addList.add(1003);
        List<Integer> delList = new ArrayList<>();
        delList.add(1001006);
        delList.add(1001007);
        roleService.assignRoleAuthc(1, addList, delList);
    }
}