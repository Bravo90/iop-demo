package io;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class MyBufferedInputStream extends BufferedInputStream {

    public MyBufferedInputStream(InputStream in) {
        super(in);
    }

    public MyBufferedInputStream(InputStream in, int size) {
        super(in, size);
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "MyBufferedInputStream [count=" + count + ", pos=" + pos + ", markpos="
                + markpos + ", marklimit=" + marklimit + "]";
    }

}
