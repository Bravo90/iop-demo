package com.sitech.billing.system.rbac.controller;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

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

    @GetMapping("/assign/{userId}")
    @RequiresPermissions(value = {PERMISSION_USER_ROLE}, logical = Logical.OR)
    public JsonResult assignRole(@PathVariable Integer userId) {
        User user = new User();
        user.setUserId(userId);
        List<Role> allRoles = roleService.listRoles();
        List<Role> userRoles = roleService.listRoleByUser(user);
        Map<String, List<Role>> roleMap = new HashMap<>();
        roleMap.put("userRoles", userRoles);
        roleMap.put("allRoles", allRoles);
        return JsonResult.success(roleMap);
    }

    @PostMapping("/assign")
    @RequiresPermissions(value = {PERMISSION_USER_ASSIGN}, logical = Logical.OR)
    public JsonResult saveAssignRole(@RequestParam Integer userId, @RequestParam String roleIds) {

        User user = new User();
        user.setUserId(userId);
        List<Role> roles = new ArrayList<>();
        if (!"".equals(roleIds) && roleIds != null) {
            String[] roleIdArr = roleIds.split(",");
            for (String roleId : roleIdArr) {
                Role role = new Role();
                role.setRoleId(Integer.parseInt(roleId));
                roles.add(role);
            }
        }
        roleService.saveUserRoles(user, roles);
        return JsonResult.success("角色分配成功");
    }
}
