package com.sitech.billing.system.rbac.service;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.system.rbac.model.User;

public interface UserService {

    PageInfo<User> listAllUser(Integer pageNum, Integer pageSize);

    User getUserById(Integer userId);

    User getUserByUsername(String username);

    Integer saveUser(User user);

    Integer updateUser(User user);

    Integer deleteUserById(Integer userId);
}
