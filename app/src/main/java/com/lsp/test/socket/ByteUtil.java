package com.lsp.test.socket;

/**
 * Created by xian on 2015/11/13.
 */
public class ByteUtil {
    /**
     * 合并两个byte数组
     *
     * @param byte_1 数组one
     * @param byte_2 数组two
     * @return 合并后的新数组
     */
    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

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
