package com.sitech.billing.system.rbac.service.impl;


import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.system.rbac.dao.UserMapper;
import com.sitech.billing.system.rbac.model.User;
import com.sitech.billing.system.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> listAllUser(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.listAllUser();
        PageInfo pageInfo = new PageInfo(users);
        return pageInfo;
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer deleteUserById(Integer userId) {
        return userMapper.deleteUserById(userId);
    }
}
