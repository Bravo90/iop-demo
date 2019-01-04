package com.sitech.billing.system.rbac.controller;

import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.rbac.model.Authc;
import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.model.ZTreeVO;
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
@RequestMapping("/role")
@Slf4j
public class RoleController extends BaseController {


    @RequestMapping("/page")
    @RequiresPermissions(value = {PERMISSION_ROLE_PAGE}, logical = Logical.OR)
    public ModelAndView page() {
        return new ModelAndView("rbac/role");
    }

    @GetMapping("/list")
    @RequiresPermissions(value = {PERMISSION_ROLE_LIST}, logical = Logical.OR)
    public JsonResult getRoles() {
        PageInfo<Role> roles = roleService.listAllRoles(1, 10);
        return JsonResult.success(roles);
    }

    @GetMapping("/{roleId}")
    @RequiresPermissions(value = {PERMISSION_ROLE_SINGLE}, logical = Logical.OR)
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
    @RequiresPermissions(value = {PERMISSION_ROLE_ADD}, logical = Logical.OR)
    public JsonResult addRole(@RequestBody Role role) {
        if ("".equals(role.getRoleName())) {
            return JsonResult.error(ErrorMsgEnum.ROLE_NAME_ALREADY_EXIST);
        }
        roleService.saveRole(role);
        return JsonResult.success("保存成功");
    }

    @DeleteMapping("/{roleId}")
    @RequiresPermissions(value = {PERMISSION_ROLE_DELETE}, logical = Logical.OR)
    public JsonResult deleteRole(@PathVariable Integer roleId) {
        roleService.delRoleByRoleId(roleId);
        return JsonResult.success("删除成功");
    }

    @PutMapping("/{roleId}")
    @RequiresPermissions(value = {PERMISSION_ROLE_UPDATE}, logical = Logical.OR)
    public JsonResult updateRole(@PathVariable Integer roleId, @RequestBody Role role) {
        role.setRoleId(roleId);
        roleService.updateRole(role);
        return JsonResult.success("更新成功");
    }

    @GetMapping("/authc/{roleId}")
    @RequiresPermissions(value = {PERMISSION_ROLE_AUTHC}, logical = Logical.OR)
    public JsonResult getRoleAuthc(@PathVariable Integer roleId) {
        List<ZTreeVO> zTreeVOS = new ArrayList<>();
        Role role = new Role();
        role.setRoleId(roleId);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        List<Authc> roleAuthc = authcService.listAuthcByRole(roleList);
        List<Authc> parentAuthc = authcService.listParentAuthc();
        List<Authc> childernAuthc = authcService.listChildrenAuthc();
        /*处理关系表数据*/
        Map<Integer, Boolean> roleAuthcMap = new HashMap<>();
        for (Authc ra : roleAuthc) {
            roleAuthcMap.put(ra.getAuthcId(), true);
        }
        /* 遍历子节点list*/
        Map<Integer, List<ZTreeVO>> childrenZTreeMap = new HashMap<>();
        for (Authc authc : childernAuthc) {
            ZTreeVO vo = new ZTreeVO();
            Integer autchId = authc.getAuthcId();
            Integer pId = autchId / AUTHC_MIN_LIMIT;
            if (roleAuthcMap.get(autchId) != null && roleAuthcMap.get(autchId)) {
                vo.setChecked(true);
            }
            vo.setName(authc.getAuthcDesc());
            vo.setAuthcId(autchId);
            vo.setChildren(null);
            List<ZTreeVO> list = childrenZTreeMap.get(pId);
            if (list != null) {
                list.add(vo);
            } else {
                list = new ArrayList<>();
                list.add(vo);
                childrenZTreeMap.put(pId, list);
            }
        }
        for (Authc authc : parentAuthc) {
            ZTreeVO vo = new ZTreeVO();
            Integer authcId = authc.getAuthcId();
            /*查询子集*/
            List<ZTreeVO> children = childrenZTreeMap.get(authcId);
            if (children != null) {
                vo.setChildren(children);
            }
            /*处理关系表*/
            if (roleAuthcMap.get(authcId) != null && roleAuthcMap.get(authcId)) {
                vo.setChecked(true);
            }
            vo.setName(authc.getAuthcDesc());
            vo.setAuthcId(authcId);
            zTreeVOS.add(vo);
        }
        return JsonResult.success(zTreeVOS);
    }

    @PutMapping("/assign")
    @RequiresPermissions(value = {PERMISSION_ROLE_ASSIGN}, logical = Logical.OR)
    public JsonResult assignRoleAuthc(@RequestParam(name = "roleId") Integer roleId,
                                      @RequestParam(name = "add") String add,
                                      @RequestParam(name = "del") String del) {

        List<Integer> addList = new ArrayList<>();
        List<Integer> delList = new ArrayList<>();


        if (!"".equals(add)) {
            String[] adds = add.split(",");
            for (String s : adds) {
                addList.add(Integer.parseInt(s));
            }
        }
        if (!"".equals(del)) {
            String[] dels = del.split(",");
            for (String s : dels) {
                delList.add(Integer.parseInt(s));
            }
        }

        roleService.assignRoleAuthc(roleId, addList, delList);
        return JsonResult.success("权限分配成功");
    }

}
