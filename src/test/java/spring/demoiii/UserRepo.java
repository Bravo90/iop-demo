package spring.demoiii;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/2/21 14:18
 */
public interface UserRepo {
    @MySql(value = "select * from user")
    void get();
}
