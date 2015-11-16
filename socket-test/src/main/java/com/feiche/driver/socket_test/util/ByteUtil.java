package com.feiche.driver.socket_test.util;

/**
 * Created by xian on 2015/11/13.
 */
public class ByteUtil {
    /**
     * @param bb
     * @param x
     * @param index
     */
    public static void putInt(byte[] bb, int x, int index) {
        bb[index + 0] = (byte) ((x >> 24) & 0xFF);
        bb[index + 1] = (byte) ((x >> 16) & 0xFF);
        bb[index + 2] = (byte) ((x >> 8) & 0xFF);
        bb[index + 3] = (byte) ((x >> 0) & 0xFF);
    }

    /**
     * @param bb
     * @param index
     * @return
     */
    public static int getInt(byte[] bb, int index) {
        return (int) ((((bb[index + 0] & 0xff) << 24)
                | ((bb[index + 1] & 0xff) << 16)
                | ((bb[index + 2] & 0xff) << 8) | ((bb[index + 3] & 0xff) << 0)));
    }
}
