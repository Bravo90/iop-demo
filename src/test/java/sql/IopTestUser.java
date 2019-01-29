package sql;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/28 17:21
 */
public class IopTestUser {

    static List<String> citys = new ArrayList<>();

    static {
        citys.add("德州");
        citys.add("日照");
        citys.add("烟台");
        citys.add("东营");
        citys.add("莱芜");
        citys.add("青岛");
        citys.add("滨州");
        citys.add("威海");
        citys.add("淄博");
        citys.add("枣庄");
        citys.add("菏泽");
        citys.add("济南");
        citys.add("泰安");
        citys.add("临沂");
        citys.add("潍坊");
        citys.add("聊城");
        citys.add("济宁");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String name = UUID.randomUUID().toString().toUpperCase().substring(0, 8);
            String city = citys.get(i % 16);
            System.out.println("('" + name + "','" + city + "'),");
        }
    }
}
