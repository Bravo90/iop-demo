package spring.demoiii;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/2/21 14:19
 */
@Configuration
@MyMapperScanner("spring.demoiii")
@ComponentScan
public class AppConfig {


}
