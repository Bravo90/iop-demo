package spring.demoiii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/2/21 14:22
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Test test = (Test)context.getBean("test");
        test.get();
    }
}
