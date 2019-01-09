package com.sitech.billing.customization.table.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.customization.table.context.TableContext;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.system.base.BaseController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用于响应table操作的API，如查询，增加、删除、更新等
 *
 * @author sunzhen
 * @date 2019/1/8 12:49
 */
@RestController
@RequestMapping("/table")
public class TableController extends BaseController {

    @GetMapping("/query")
    public JsonResult query(@RequestParam String param) {

        JSONObject jsonObject = JSONObject.parseObject(param);
        String fields = jsonObject.getString("fields");
        List<FieldValue> fieldValues = JSON.parseArray(fields, FieldValue.class);

        String orders = jsonObject.getString("order");
        List<FieldOrder> fieldOrders = JSON.parseArray(orders, FieldOrder.class);

        TableContext context = new TableContext.Builder()
                .tableConfig(1)
                .fieldValues(fieldValues)
                .fieldOrders(fieldOrders)
                .pageInfo(null)
                .build().querySqlInit();
        context.query();
        return JsonResult.success();
    }

    @PutMapping("/insert")
    public JsonResult insert(@RequestParam String param) {
        return JsonResult.success();
    }

    @DeleteMapping("/delete")
    public JsonResult delete(@RequestParam String param) {
        return JsonResult.success();
    }

    @PutMapping("/update")
    public JsonResult update(@RequestParam String param) {
        return JsonResult.success();
    }
}
