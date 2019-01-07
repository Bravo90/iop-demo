package com.sitech.billing.system.menu.service;

import com.sitech.billing.system.menu.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> listParentMenu();

    List<Menu> listRootMenu();

    Integer saveMenu(Menu menu);

    Integer deleteMenu(Integer menuId);

    Integer updateMenu(Menu menu);
}
