package com.sitech.billing.customization.table.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 界面跳转控制类
 *
 * @author sunzhen
 * @date 2019/1/8 12:49
 */
@RestController
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/table/{viewId}")
    public ModelAndView tablePage(@PathVariable Integer viewId) {
        return new ModelAndView("customization/table/view")
                .addObject("viewId", viewId);
    }

    @GetMapping("/config")
    public ModelAndView configPage() {
        return new ModelAndView("");
    }


}
