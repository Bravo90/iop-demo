package com.sitech.billing.system.rbac.controller;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.system.base.BaseController;
import com.sitech.billing.system.rbac.model.Authc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/{authcId}")
    public JsonResult getAuthcById(@PathVariable Integer authcId) {
        Authc authc = authcService.getAuthcByAuthcId(authcId);
        return JsonResult.success(authc);
    }

    @GetMapping("/plist")
    public JsonResult parentAuthcList() {
        List<Authc> parentAuthcs = authcService.listParentAuthc();
        return JsonResult.success(parentAuthcs);
    }

    @GetMapping("/clist/{parentAuthcId}")
    public JsonResult childrenAuthcList(@PathVariable Integer parentAuthcId) {
        List<Authc> authcs = authcService.listAuthcByParentId(parentAuthcId);
        return JsonResult.success(authcs);
    }

    @PostMapping("/add")
    public JsonResult addAuthc(@RequestBody Authc authc) {
        if ("".equals(authc.getAuthcName())) {
            return JsonResult.error(ErrorMsgEnum.AUTHC_NAME_ALREADY_EXIST);
        }
        if (authc.getAuthcId().equals(AUTHC_MIN_LIMIT)) {
            Integer authcId = authcService.getParentAuthcId();
            authc.setAuthcId(authcId);
        } else {
            Integer authcId = authcService.getChildAuthcId(authc.getAuthcId());
            authc.setAuthcId(authcId);
        }

        authcService.saveAuthc(authc);
        return JsonResult.success("保存成功");
    }

    @GetMapping("/authcname/{authcName}")
    public JsonResult getAuthcByAuthcName(@PathVariable String authcName) {
        Authc authc = authcService.getAuthcByAuthcName(authcName);
        if (authc == null) {
            return JsonResult.success();
        }
        return JsonResult.error(ErrorMsgEnum.AUTHC_NAME_ALREADY_EXIST);
    }

    @DeleteMapping("/{authcId}")
    public JsonResult deleteAuthc(@PathVariable Integer authcId) {
        authcService.delAuthcByAuthcId(authcId);
        return JsonResult.success("删除成功");
    }

    @PutMapping("/{authcId}")
    public JsonResult updateAuthc(@PathVariable Integer authcId, @RequestBody Authc authc) {
        authc.setAuthcId(authcId);
        authcService.updateAuthc(authc);
        return JsonResult.success("删除成功");
    }
}
