package com.sitech.billing.helloworld.controller;

import com.sitech.billing.common.anno.SystemLog;
import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.helloworld.model.HelloWorld;
import com.sitech.billing.helloworld.service.HelloWorldService;
import com.sitech.billing.system.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class HelloWorldController extends BaseController {

    @Resource
    private HelloWorldService helloWorldService;

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        System.out.println(request.getPathInfo());
        return "error";
    }

    @RequiresPermissions(value = "user:insert")
    @GetMapping("/helloworld/{id}")
    public String welcome(Model model,@PathVariable(value = "id",required = true) Integer id) {
        HelloWorld hw = helloWorldService.getHelloWordMsgById(id);
        model.addAttribute("hw", hw);
        return "helloworld/helloworld";
    }

    @SystemLog(module = "Hello:Ajax测试", LogMessage = "测试对ajax请求异常处理")
    @RequiresPermissions(value = "user:insert")
    @GetMapping("/ajaxRequest")
    @ResponseBody
    public JsonResult ajaxRequest() {
        return JsonResult.success("请求成功");
    }

}
