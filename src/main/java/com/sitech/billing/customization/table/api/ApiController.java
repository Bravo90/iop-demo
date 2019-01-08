package com.sitech.billing.customization.table.api;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.system.base.BaseController;
import org.springframework.web.bind.annotation.*;

/**
 * @description TODO
 * @authc sunzhen
 * @date 2019/1/8 12:49
 */
@RestController
@RequestMapping("/table")
public class ApiController extends BaseController {

    @GetMapping("/query")
    public JsonResult query(){
        return JsonResult.success();
    }

    @PutMapping("/insert")
    public JsonResult insert(){
        return  JsonResult.success();
    }

    @DeleteMapping("/delete")
    public JsonResult delete(){
        return  JsonResult.success();
    }

    @PutMapping("/update")
    public JsonResult update(){
        return  JsonResult.success();
    }
}
