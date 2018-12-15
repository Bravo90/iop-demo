package com.sitech.billing.helloworld.service;

import com.sitech.billing.MainApplication;
import com.sitech.billing.helloworld.model.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class HelloWorldServiceTest {


    @Resource
    private HelloWorldService helloWorldService;

    @Test
    public void getAllHelloWordMsg() {
        List<HelloWorld> hws = helloWorldService.getAllHelloWordMsg();
        System.out.println(hws.toString());
    }

    @Test
    public void getHelloWordMsg() {
        HelloWorld helloWordMsg = helloWorldService.getHelloWordMsgById(1);
        System.out.println(helloWordMsg.toString());
    }

    @Test
    public void saveHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();

        helloWorld.setId(2);
        helloWorld.setName("前端组");
        helloWorld.setDescription("智慧运营团队前端组");
        helloWorld.setCompany("sitech");
        helloWorld.setContributors("sunzhen");
        helloWorld.seteMail("sunzhen@si-tech.com.cn");
        helloWorld.setTel("1989898981");
        helloWorld.setDevelopers("sunzhen");

        int i = helloWorldService.saveHelloWorld(helloWorld);
        System.out.println(i);
    }
}