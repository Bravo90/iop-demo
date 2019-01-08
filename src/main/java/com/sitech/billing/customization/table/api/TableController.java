package com.sitech.billing.customization.table.api;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.system.base.BaseController;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.*;

/**
 * 用于响应table操作的API，如查询，增加、删除、更新等
 * @author sunzhen
 * @date 2019/1/8 12:49
 */
@RestController
@RequestMapping("/table")
public class TableController extends BaseController {

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
