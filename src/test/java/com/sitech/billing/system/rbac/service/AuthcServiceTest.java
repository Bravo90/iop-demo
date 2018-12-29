package com.sitech.billing.system.rbac.service;

import com.sitech.billing.MainApplication;
import com.sitech.billing.system.rbac.model.Authc;
import com.sitech.billing.system.rbac.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class AuthcServiceTest {

    @Resource
    private AuthcService authcService;

    @Test
    public void listAllAuthc() {
        System.out.println(authcService.listAllAuthc(1, 10));
    }

    @Test
    public void saveAuthc() {
        Authc authc = new Authc();
        authc.setAuthcName("sys:demo");
        authc.setAuthcDesc("这是测试权限");
        Integer i = authcService.saveAuthc(authc);
        System.out.println(i);
    }

    @Test
    public void delAuthcByAuthcId() {
        Integer i = authcService.delAuthcByAuthcId(1);
        System.out.println(i);
    }

    @Test
    public void updateAuthc() {
        Authc authc = new Authc();
        authc.setAuthcId(1);
        authc.setAuthcName("sys:demo:test");
        Integer i = authcService.updateAuthc(authc);
        System.out.println(i);
    }

    @Test
    public void getAuthcByAuthcId() {
        Authc authc = authcService.getAuthcByAuthcId(1);
        System.out.println(authc);
    }

    @Test
    public void listAuthcByRole() {
        List<Role> list = new ArrayList<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        Role role2 = new Role();
        role2.setRoleId(3);
        list.add(role1);
        list.add(role2);
        List<Authc> set = authcService.listAuthcByRole(list);
        System.out.println(set);
    }

    @Test
    public void listParentAuthc() {
        List<Authc> authcs = authcService.listParentAuthc();
        System.out.println(authcs);
    }

    @Test
    public void listAuthcByParentId() {
        Integer id = 1001;
        List<Authc> authcs = authcService.listAuthcByParentId(id);
        System.out.println(authcs);
    }
}