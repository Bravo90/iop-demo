package spring.demoi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        String[] names = context.getBeanDefinitionNames();
        System.out.println("=================");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
