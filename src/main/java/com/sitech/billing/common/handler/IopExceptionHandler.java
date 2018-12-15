package com.sitech.billing.common.handler;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.exception.IopException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统中的异常统一处理，包括非运行时异常、运行时异常、自定义异常等，要求在Controller中对于异常处理
 * 1）在try...catch...块中，必须处理异常，处理不成，直接抛出，不可不做任何处理导致异常丢失
 * 2）非运行时异常，不用try...catch...处理时可以直接抛出
 * 3）运行时异常，该类会自动捕获处理，无需程序中处理
 *
 * @author sunzhen
 * @date 2018-11-30 23:10:32
 */
@RestControllerAdvice
public class IopExceptionHandler {

    @ExceptionHandler(IopException.class)
    public JsonResult exceptionHand(IopException e) {
        return JsonResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public JsonResult exceptionHand(Exception e) {
        e.printStackTrace();
        return JsonResult.error(e.getMessage());
    }

    @ExceptionHandler({UnauthorizedException.class })
    public Object handleUnauthorized(ShiroException e, HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
            return JsonResult.error("用户权限不足");
        } else {
            return new ModelAndView("error").addObject("msg", "用户权限不足");
        }
    }
}
