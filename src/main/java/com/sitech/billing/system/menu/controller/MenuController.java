package com.sitech.billing.system.menu.controller;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.menu.model.Menu;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController extends BaseController {

    @RequestMapping("/page")
    // @RequiresPermissions(value = {PERMISSION_AUTHC_PAGE}, logical = Logical.OR)
    public ModelAndView page() {
        return new ModelAndView("menu/menu");
    }

    @RequestMapping("/plist")
    public JsonResult getParentMenus() {
        List<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setMenuId(1001);
        menu.setMenuName("系统管理");
        menu.setMenuUrl("#");
        menu.setMenuLevel(1);
        menu.setParentMenuId(0);
        menu.setMenuOrder(1);
        menu.setAucthcId(0);

        menus.add(menu);
        return JsonResult.success(menus);
    }

}
