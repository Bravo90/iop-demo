package com.sitech.billing.system.rbac.controller;

import com.sitech.billing.system.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/authc")
public class AuthcController extends BaseController{

    @RequestMapping("/page")
    public ModelAndView page(){
        return new ModelAndView("rbac/authc");
    }
}
