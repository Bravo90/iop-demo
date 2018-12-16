package com.sitech.billing.system.rbac.dao;

import com.sitech.billing.system.rbac.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> listAllUser();

    User getUserById(@Param("userId") Integer userId);

    Integer saveUser(@Param("user") User user);

    Integer updateUser(@Param("user") User user);

    Integer deleteUserById(@Param("userId") Integer userId);
}
