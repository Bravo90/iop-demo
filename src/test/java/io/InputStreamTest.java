package io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * TODO
 *
 * @author sunzhen
 * @date 2019/3/5 14:10
 */
public class InputStreamTest {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\elklog\\elk\\1234.txt");

        InputStream in = new FileInputStream(file);
        MyBufferedInputStream fin = new MyBufferedInputStream(in, 1024);
        int readInt = 0;
        while ((readInt = fin.read()) != -1) {
            System.out.println(readInt);
        }

        System.out.println(-28 & 0xff);
        System.out.println(-72 & 0xff);
        System.out.println(-83 & 0xff);


    }
}
