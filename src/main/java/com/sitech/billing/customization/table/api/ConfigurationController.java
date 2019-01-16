package com.sitech.billing.customization.table.api;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.customization.table.context.TableContext;
import com.sitech.billing.customization.table.model.Col;
import com.sitech.billing.system.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用于配置的API接口
 *
 * @author sunzhen
 * @date 2019/1/8 12:49
 */
@RestController
@RequestMapping("/config")
public class ConfigurationController extends BaseController {

    @GetMapping("/{viewId}")
    public JsonResult getConfig(@PathVariable Integer viewId) {
        TableContext context = new TableContext.Builder().tableConfig(viewId).jdbc(jdbcTemplate).buildView();
        List<Col> cols = context.getCols();
        return JsonResult.success(cols);
    }
}
