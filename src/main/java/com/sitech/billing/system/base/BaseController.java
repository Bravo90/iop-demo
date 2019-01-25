package com.sitech.billing.system.base;

import com.sitech.billing.datainput.service.DataInputService;
import com.sitech.billing.datainput.service.DataInputTableService;
import com.sitech.billing.system.rbac.service.AuthcService;
import com.sitech.billing.system.rbac.service.RoleService;
import com.sitech.billing.system.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

    /*自动注入的request对象*/
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /*权限ID大于1000,从1001开始*/
    protected static final Integer AUTHC_MIN_LIMIT = 1000;

    /*用户管理相关权限*/
    protected static final String PERMISSION_USER_PAGE = "sys:user:page";
    protected static final String PERMISSION_USER_LIST = "sys:user:list";
    protected static final String PERMISSION_USER_SINGLE = "sys:user:single";
    protected static final String PERMISSION_USER_ADD = "sys:user:add";
    protected static final String PERMISSION_USER_UPDATE = "sys:user:update";
    protected static final String PERMISSION_USER_DELETE = "sys:user:delete";
    protected static final String PERMISSION_USER_ROLE = "sys:user:role";
    protected static final String PERMISSION_USER_ASSIGN = "sys:user:assign";
    /*角色管理相关权限*/
    protected static final String PERMISSION_ROLE_PAGE = "sys:role:page";
    protected static final String PERMISSION_ROLE_LIST = "sys:role:list";
    protected static final String PERMISSION_ROLE_SINGLE = "sys:role:single";
    protected static final String PERMISSION_ROLE_ADD = "sys:role:add";
    protected static final String PERMISSION_ROLE_DELETE = "sys:role:delete";
    protected static final String PERMISSION_ROLE_UPDATE = "sys:role:update";
    protected static final String PERMISSION_ROLE_AUTHC = "sys:role:authc";
    protected static final String PERMISSION_ROLE_ASSIGN = "sys:role:assign";
    /*权限管理相关权限*/
    protected static final String PERMISSION_AUTHC_PAGE = "sys:authc:page";
    protected static final String PERMISSION_AUTHC_SINGLE = "sys:authc:single";
    protected static final String PERMISSION_AUTHC_LIST = "sys:authc:list";
    protected static final String PERMISSION_AUTHC_ADD = "sys:authc:add";
    protected static final String PERMISSION_AUTHC_DELETE = "sys:authc:delete";
    protected static final String PERMISSION_AUTHC_UPDATE = "sys:authc:update";

    /*自动注入的service*/
    @Resource
    protected UserService userService;
    @Resource
    protected AuthcService authcService;
    @Resource
    protected RoleService roleService;

    @Resource
    protected DataInputService dataInputService;
    @Resource
    protected DataInputTableService dataInputTableService;
}
