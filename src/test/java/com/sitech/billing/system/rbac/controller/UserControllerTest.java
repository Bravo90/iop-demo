package com.sitech.billing.system.rbac.controller;

import com.alibaba.fastjson.JSON;
import com.sitech.billing.MainApplication;
import com.sitech.billing.system.rbac.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MainApplication.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Resource
    private SecurityManager securityManager;
    private MockMvc mockMvc;
    private Subject subject;
    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;


    private void login(String username, String password) {
        subject = new WebSubject.Builder(mockHttpServletRequest, mockHttpServletResponse)
                .buildWebSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(username, password, true);
        subject.login(token);
        ThreadContext.bind(subject);

    }

    @Before
    public void before() {
        mockHttpServletRequest = new MockHttpServletRequest(webApplicationContext.getServletContext());
        mockHttpServletResponse = new MockHttpServletResponse();

        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        mockHttpServletRequest.setSession(mockHttpSession);

        SecurityUtils.setSecurityManager(securityManager);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        login("admin", "123456");
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/user/list")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/user/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addUser() throws Exception {

        User u = new User();
        u.setUsername("");
        u.setNickname("");
        u.setPassword("");
        String json = JSON.toJSONString(u);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUser() throws Exception {

        User u = new User();
        u.setUserId(38);
        u.setNickname("开发人员");
        u.setPassword("1234567");
        String json = JSON.toJSONString(u);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/user/3")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());
    }
}