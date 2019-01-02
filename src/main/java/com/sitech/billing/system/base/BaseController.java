package com.sitech.billing.system.base;

import com.sitech.billing.system.rbac.service.AuthcService;
import com.sitech.billing.system.rbac.service.RoleService;
import com.sitech.billing.system.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

    /**
     * 自动注入的request对象
     */
    @Autowired
    protected HttpServletRequest request;

    /**
     * 权限父级id下限
     **/
    protected static final Integer AUTHC_MIN_LIMIT = 1000;

    /**
     * 用户管理相关权限
     **/
    protected static final String PERMISSION_USER_PAGE = "sys:user:page";
    protected static final String PERMISSION_USER_LIST = "sys:user:list";
    protected static final String PERMISSION_USER_SINGLE = "sys:user:single";
    protected static final String PERMISSION_USER_ADD = "sys:user:add";
    protected static final String PERMISSION_USER_UPDATE = "sys:user:update";
    protected static final String PERMISSION_USER_DELETE = "sys:user:delete";
    protected static final String PERMISSION_USER_ROLE = "sys:user:role";
    protected static final String PERMISSION_USER_ASSIGN = "sys:user:assign";

    /** 角色管理相关权限 **/

    /**
     * 权限管理相关权限
     **/

    /**
     * 自动注入的service
     */
    @Resource
    protected UserService userService;
    @Resource
    protected AuthcService authcService;
    @Resource
    protected RoleService roleService;

}
