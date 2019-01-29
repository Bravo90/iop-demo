package sql;

import java.util.UUID;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/28 17:34
 */
public class IopTestUserLog {

    public static void main(String[] args) {
        for (int i = 0; i < 1500; i++) {
            int userId = i / 99 + 1;
            String logTime = "20190" + (i % 8 + 1) + "" + (i % 10 + 18) + "120000";
            String msg = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            System.out.println("(" + userId + ",'" + logTime + "','" + msg + "')");
        }
    }
}
