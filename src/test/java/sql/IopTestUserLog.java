package sql;

import java.util.Random;
import java.util.UUID;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/28 17:34
 */
public class IopTestUserLog {

    public static void main(String[] args) {
        for (int i = 0; i < 2500; i++) {
            int userId = getUserId();
            int logId = i + 1;
            String logTime = "2019"
                    + getMonth()
                    + getDay()
                    + getHour()
                    + getMS()
                    + getMS();
            String msg = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            System.out.println("(" + logId + "," + userId + ",'" + logTime + "','" + msg + "'),");
        }
    }

    private static int getUserId() {
        Random random = new Random();
        return random.nextInt(99) + 1;
    }

    private static String getMonth() {
        String month;
        Random random = new Random();
        int m = random.nextInt(11) + 1;
        if (m < 10) {
            month = "0" + m;
        } else {
            month = "" + m;
        }
        return month;
    }

    private static String getDay() {
        String day;
        Random random = new Random();
        int d = random.nextInt(28) + 1;
        if (d < 10) {
            day = "0" + d;
        } else {
            day = "" + d;
        }

        return day;
    }

    private static String getHour() {
        String hour;
        Random random = new Random();
        int h = random.nextInt(12);
        if (h < 10) {
            hour = "0" + h;
        } else {
            hour = "" + h;
        }
        return hour;
    }

    private static String getMS() {
        String ms;
        Random random = new Random();
        int m = random.nextInt(60);
        if (m < 10) {
            ms = "0" + m;
        } else {
            ms = "" + m;
        }
        return ms;
    }

}
