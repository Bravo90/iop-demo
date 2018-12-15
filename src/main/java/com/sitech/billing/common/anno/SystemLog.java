package com.sitech.billing.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问记录注解，用于Controller的方法，用于标记该Controller的模块和功能
 *
 * @author sunzhen
 * @date 2018-12-01 09:42:12
 * @see com.sitech.billing.common.handler.SystemLogHandler
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemLog {
    String module() default "";

    String LogMessage() default "";
}
