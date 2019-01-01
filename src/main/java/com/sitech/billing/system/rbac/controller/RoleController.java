package com.sitech.billing.system.rbac.controller;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.rbac.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController extends BaseController {


    @RequestMapping("/page")
    public ModelAndView page() {
        return new ModelAndView("rbac/role");
    }

    @GetMapping("/list")
    public JsonResult getRoles() {
        PageInfo<Role> roles = roleService.listAllRoles(1, 10);
        return JsonResult.success(roles);
    }

    @GetMapping("/{roleId}")
    public JsonResult getRoleByRoleId(@PathVariable Integer roleId) {
        Role role = roleService.getRoleByRoleId(roleId);
        return JsonResult.success(role);
    }

    @GetMapping("/rolename/{roleName}")
    public JsonResult getRoleByRoleName(@PathVariable String roleName) {
        Role role = roleService.getRoleByRoleName(roleName);
        if (role == null) {
            return JsonResult.success();
        }
        return JsonResult.error(ErrorMsgEnum.ROLE_NAME_ALREADY_EXIST);
    }

    @PostMapping("/add")
    public JsonResult addRole(@RequestBody Role role) {
        if ("".equals(role.getRoleName())) {
            return JsonResult.error(ErrorMsgEnum.ROLE_NAME_ALREADY_EXIST);
        }
        roleService.saveRole(role);
        return JsonResult.success("保存成功");
    }

    @DeleteMapping("/{roleId}")
    public JsonResult deleteRole(@PathVariable Integer roleId) {
        roleService.delRoleByRoleId(roleId);
        return JsonResult.success("删除成功");
    }

    @PutMapping("/{roleId}")
    public JsonResult updateRole(@PathVariable Integer roleId, @RequestBody Role role) {
        role.setRoleId(roleId);
        roleService.updateRole(role);
        return JsonResult.success("更新成功");
    }

}
