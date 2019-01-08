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
    private String menuUrl = "#";
    private int menuLevel = 1;
    private int parentMenuId = 0;
    private int menuOrder;
    private int aucthcId = 0;
}
