package com.sitech.billing.helloworld.service;

import com.sitech.billing.helloworld.model.HelloWorld;

import java.util.List;

public interface HelloWorldService {

    List<HelloWorld> getAllHelloWordMsg();

    HelloWorld getHelloWordMsgById(Integer id);

    int saveHelloWorld(HelloWorld helloWorld);
}
