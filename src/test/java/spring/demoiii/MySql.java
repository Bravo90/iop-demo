package spring.demoiii;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/2/21 14:07
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MySql {
    String value();
}
