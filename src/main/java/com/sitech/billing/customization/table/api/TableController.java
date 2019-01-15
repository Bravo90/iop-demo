package com.sitech.billing.customization.table.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.customization.table.context.TableContext;
import com.sitech.billing.customization.table.model.request.FieldOrder;
import com.sitech.billing.customization.table.model.request.FieldValue;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import com.sitech.billing.customization.table.type.DialectType;
import com.sitech.billing.system.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用于响应table操作的API，如查询，增加、删除、更新等
 *
 * @author sunzhen
 * @date 2019/1/8 12:49
 */
@RestController
@RequestMapping("/table")
@Slf4j
public class TableController extends BaseController {

    @GetMapping("/query")
    public JsonResult query(@RequestParam String param) {

        JSONObject jsonObject = JSONObject.parseObject(param);
        Integer tableId = jsonObject.getInteger("tableId");
        String fields = jsonObject.getString("fields");
        List<FieldValue> fieldValues = JSON.parseArray(fields, FieldValue.class);
        String orders = jsonObject.getString("order");
        List<FieldOrder> fieldOrders = JSON.parseArray(orders, FieldOrder.class);
        RequestPageInfo pageInfo = JSON.parseObject(jsonObject.getString("page"), RequestPageInfo.class);

        //获得context
        TableContext context =
                new TableContext.Builder().dbDialect(DialectType.MYSQL).jdbc(jdbcTemplate)
                .tableConfig(tableId)
                .fieldValues(fieldValues)
                .fieldOrders(fieldOrders)
                .pageInfo(pageInfo)
                .build();
        //查询
        try {
            PageInfo<Map<String, Object>> pageResult = context.queryByPage();
            return JsonResult.success(pageResult);
        } catch (Exception e) {
            log.error(e.toString());
            return JsonResult.error("查询失败");
        }

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
