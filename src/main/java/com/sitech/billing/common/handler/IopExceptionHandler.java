package com.sitech.billing.common.handler;

import com.sitech.billing.common.bean.JsonResult;
import com.sitech.billing.common.exception.IopException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    private Logger logger = LoggerFactory.getLogger(IopExceptionHandler.class);

    private static final String X_REQUESTED_WITH = "X-Requested-With";
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    @ExceptionHandler(IopException.class)
    public Object exceptionHand(IopException e, HttpServletRequest request) {
        logger.error(e.getMessage());
        String requestType = request.getHeader(X_REQUESTED_WITH);
        if (XML_HTTP_REQUEST.equalsIgnoreCase(requestType)) {
            return JsonResult.error(e.getMessage());
        } else {
            return new ModelAndView("login/login")
                    .addObject("msg", e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public JsonResult exceptionHand(Exception e) {
        logger.error(e.getMessage());
        return JsonResult.error(e.getMessage());
    }

    @ExceptionHandler(UnknownAccountException.class)
    public Object handleUnknownAccountException(UnknownAccountException e, HttpServletRequest request) {
        logger.error(e.getMessage());
        String requestType = request.getHeader(X_REQUESTED_WITH);
        if (XML_HTTP_REQUEST.equalsIgnoreCase(requestType)) {
            return JsonResult.error("用户不存在");
        } else {
            return new ModelAndView("login/login")
                    .addObject("msg", "用户不存在");
        }
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public Object handleIncorrectCredentialsException(IncorrectCredentialsException e, HttpServletRequest request) {
        logger.error(e.getMessage());
        String requestType = request.getHeader(X_REQUESTED_WITH);
        if (XML_HTTP_REQUEST.equalsIgnoreCase(requestType)) {
            return JsonResult.error("密码不正确");
        } else {
            return new ModelAndView("login/login")
                    .addObject("msg", "密码不正确");
        }
    }

    @ExceptionHandler({UnauthorizedException.class})
    public Object handleUnauthorized(ShiroException e, HttpServletRequest request) {
        logger.error(e.getMessage());
        String requestType = request.getHeader(X_REQUESTED_WITH);
        if (XML_HTTP_REQUEST.equalsIgnoreCase(requestType)) {
            return JsonResult.error("用户权限不足");
        } else {
            return new ModelAndView("error").addObject("msg", "用户权限不足");
        }
    }
}
