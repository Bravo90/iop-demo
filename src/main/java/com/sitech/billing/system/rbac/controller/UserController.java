package com.sitech.billing.system.rbac.controller;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.rbac.model.User;
import com.sitech.billing.system.rbac.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public JsonResult getUsers() {
        PageInfo<User> users = userService.listAllUser(1, 10);
        System.out.println(users);
        return JsonResult.success(users);
    }

    @GetMapping("/update")
    @ResponseBody
    public JsonResult updateUser() {

        return null;
    }
}
