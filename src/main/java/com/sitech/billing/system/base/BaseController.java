package com.sitech.billing.system.base;

import com.sitech.billing.system.rbac.service.AuthcService;
import com.sitech.billing.system.rbac.service.RoleService;
import com.sitech.billing.system.rbac.service.UserService;

import javax.annotation.Resource;

public class BaseController {


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


    @Resource
    protected UserService userService;
    @Resource
    protected AuthcService authcService;
    @Resource
    protected RoleService roleService;

}
