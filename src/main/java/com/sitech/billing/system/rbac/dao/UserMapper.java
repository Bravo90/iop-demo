package com.sitech.billing.system.rbac.dao;

import com.sitech.billing.system.rbac.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> listAllUser();

    User getUserById(@Param("userId") Integer userId);

    User getUserByUsername(@Param("username") String username);

    Integer saveUser(@Param("user") User user);

    Integer updateUser(@Param("user") User user);

    Integer deleteUserById(@Param("userId") Integer userId);
}
