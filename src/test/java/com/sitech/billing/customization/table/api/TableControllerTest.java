package com.sitech.billing.customization.table.api;

import com.sitech.billing.MainApplication;
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

/**
 * 测试类
 *
 * @author sunzhen
 * @date 2019/1/8 16:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MainApplication.class)
public class TableControllerTest {
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
        login("admin", "21232f297a57a5a743894a0e4a801fc3");
    }

    @Test
    public void query() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/table/query")
                        .param("param", QUERY_PARAM)
                        .accept(MediaType.APPLICATION_JSON)
        );
    }

    @Test
    public void insert() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    private static final String QUERY_PARAM = "{\"tableId\":\"1\",\"page\":{\"pageSize\":1,\"pageNum\":1}}";
    //{"tableId":"1","fields":[{"name":"user_id","value":["1","2","3"]}],"order":[{"field":"user_id","type":"desc"}],"page":{"pageSize":1,"pageNum":1}}
}