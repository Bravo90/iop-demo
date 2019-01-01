package com.sitech.billing.system.rbac.controller;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.rbac.model.Authc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/authc")
@Slf4j
public class AuthcController extends BaseController {

    @RequestMapping("/page")
    public ModelAndView page() {
        return new ModelAndView("rbac/authc");
    }

    @GetMapping("/plist")
    public JsonResult parentAuthcList() {
        List<Authc> parentAuthcs = authcService.listParentAuthc();
        return JsonResult.success(parentAuthcs);
    }
}
