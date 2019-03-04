package spring.demoiii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/2/21 14:19
 */
@Service
public class Test {
    @Autowired
    UserRepo userRepo;

    public void get() {
        userRepo.get();
    }
}
