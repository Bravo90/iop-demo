package com.sitech.billing.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/1/24 13:48
 */
public abstract class IOUtils {

    public static String toString(InputStream is) throws IOException {
        List<String> list = toList(is, "utf-8");
        return String.join(" ",list);
    }

    public static List<String> toList(InputStream is) throws IOException {
        return toList(is, "utf-8");
    }

    public static List<String> toList(InputStream is, String encoding) throws IOException {
        if (is != null) {
            List<String> list = new ArrayList<>();
            InputStreamReader streamReader = new InputStreamReader(is, encoding);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            bufferedReader.close();
            streamReader.close();
            return list;
        } else {
            return null;
        }
    }
}
