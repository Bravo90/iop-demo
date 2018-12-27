package com.sitech.billing.system.rbac.controller;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.rbac.model.User;
import com.sitech.billing.system.rbac.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final String PERMISSION_USER_PAGE = "sys:user:page";
    private static final String PERMISSION_USER_LIST = "sys:user:list";
    private static final String PERMISSION_USER_SINGLE = "sys:user:single";
    private static final String PERMISSION_USER_ADD = "sys:user:add";
    private static final String PERMISSION_USER_UPDATE = "sys:user:update";
    private static final String PERMISSION_USER_DELETE = "sys:user:delete";

    @Resource
    private UserService userService;


    @GetMapping("/page")
    @RequiresPermissions(value = {PERMISSION_USER_PAGE}, logical = Logical.OR)
    public ModelAndView page() {
        return new ModelAndView("rbac/user");
    }

    @GetMapping("/list")
    @RequiresPermissions(value = {PERMISSION_USER_LIST}, logical = Logical.OR)
    public JsonResult getUsers() {
        PageInfo<User> users = userService.listAllUser(1, 10);
        return JsonResult.success(users);
    }

    @GetMapping("/{userId}")
    @RequiresPermissions(value = {PERMISSION_USER_SINGLE}, logical = Logical.OR)
    public JsonResult getUserById(@PathVariable(value = "userId") Integer userId) {
        User user = userService.getUserById(userId);
        return JsonResult.success(user);
    }

    @GetMapping("/username/{username}")
    @RequiresPermissions(value = {PERMISSION_USER_SINGLE}, logical = Logical.OR)
    public JsonResult getUserByUsername(@PathVariable(value = "username") String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return JsonResult.success();
        }
        return JsonResult.error(ErrorMsgEnum.USERNAME_ALREADY_EXIST);
    }

    @PostMapping(value = "/add")
    @RequiresPermissions(value = {PERMISSION_USER_ADD}, logical = Logical.OR)
    public JsonResult addUser(@RequestBody User user) {
        if ("".equals(user.getUsername()) || "".equals(user.getPassword())) {
            throw new IopException(ErrorMsgEnum.USERNAME_OR_PASSWORD_IS_EMPTY);
        }
        userService.saveUser(user);
        return JsonResult.success("添加成功");
    }

    @PutMapping("/update")
    @RequiresPermissions(value = {PERMISSION_USER_UPDATE}, logical = Logical.OR)
    public JsonResult updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return JsonResult.success("更新成功");
    }

    @DeleteMapping("/{userId}")
    @RequiresPermissions(value = {PERMISSION_USER_DELETE}, logical = Logical.OR)
    public JsonResult deleteUser(@PathVariable(value = "userId") Integer userId) {
        userService.deleteUserById(userId);
        return JsonResult.success("删除成功");
    }
}
