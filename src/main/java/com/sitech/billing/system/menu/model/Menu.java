package com.sitech.billing.system.menu.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Menu {
    private int menuId;
    private String menuName;
    private String menuUrl;
    private int menuLevel;
    private int  parentMenuId;
    private int menuOrder;
    private int aucthcId;
}
