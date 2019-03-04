package spring.demoiii;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/2/21 14:06
 */
@Import(MyMapperScannerRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMapperScanner {
    String value();
}
