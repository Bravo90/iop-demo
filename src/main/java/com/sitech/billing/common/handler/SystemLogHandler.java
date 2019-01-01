package com.sitech.billing.common.handler;

import com.sitech.billing.common.anno.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 *  注解处理类，使用AOP记录每次访问操作
 * @author sunzhen
 * @date 2018-12-01 09:45:21
 */
@Aspect
@Component
@Slf4j
public class SystemLogHandler {


    @Pointcut("@annotation(com.sitech.billing.common.anno.SystemLog)")
    public void log(){}

    @Before("log()")
    public void sysLog(JoinPoint joinPoint){

       MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

       SystemLog systemLog =  methodSignature.getMethod().getAnnotation(SystemLog.class);

       String module =  systemLog.module();

       String mgs =  systemLog.LogMessage();

       log.info("模块{}，方法{}",module,mgs);
    }
}
