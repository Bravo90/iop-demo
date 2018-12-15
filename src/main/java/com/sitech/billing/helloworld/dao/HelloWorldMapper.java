package com.sitech.billing.helloworld.dao;

import com.sitech.billing.helloworld.model.HelloWorld;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface HelloWorldMapper {

    List<HelloWorld> selectAll();

    HelloWorld selectOne(@Param("id") Integer id);

    int insert(@Param("helloWorld") HelloWorld helloWorld);
}
