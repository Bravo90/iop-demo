package com.sitech.billing.system.rbac.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sitech.billing.MainApplication;
import com.sitech.billing.system.rbac.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void listAllUser() {
        PageInfo<User> userPageInfo =
                userService.listAllUser(1, 10);

        System.out.println(userPageInfo);
    }

    @Test
    public void getUserById() {
        User user = userService.getUserById(2);
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        User u = new User();
        u.setUsername("authcAdmin2");
        u.setPassword("123456");
        u.setNickname("权限管理员");
        Integer i = userService.saveUser(u);
        System.out.println(" i = " + i);
    }

    @Test
    public void updateUser() {
        User u = new User();
        u.setUserId(2);
        u.setUsername("AuthcAdmin");
        Integer i = userService.updateUser(u);
        System.out.println(i);
    }

    @Test
    public void deleteUserById() {
        System.out.println(userService.deleteUserById(3));
    }
}