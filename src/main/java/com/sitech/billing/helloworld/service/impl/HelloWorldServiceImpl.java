package com.sitech.billing.helloworld.service.impl;

import com.sitech.billing.helloworld.dao.HelloWorldMapper;
import com.sitech.billing.helloworld.model.HelloWorld;
import com.sitech.billing.helloworld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

    @Autowired
    private HelloWorldMapper helloWorldMapper;

    @Override
    public List<HelloWorld> getAllHelloWordMsg() {
        return helloWorldMapper.selectAll();
    }

    @Override
    public HelloWorld getHelloWordMsgById(Integer id) {
        return helloWorldMapper.selectOne(id);
    }

    @Transactional
    @Override
    public int saveHelloWorld(HelloWorld helloWorld) {
        int i = helloWorldMapper.insert(helloWorld);
        return i;
    }
}
