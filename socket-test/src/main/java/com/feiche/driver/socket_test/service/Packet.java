package com.feiche.driver.socket_test.service;


import com.feiche.driver.socket_test.util.AtomicIntegerUtil;

/**
 * @author Administrator
 */
public class Packet {

    private int id = AtomicIntegerUtil.getIncrementID();
    private byte[] data;

    public int getId() {
        return id;
    }

    public void pack(String txt) {
        data = txt.getBytes();
    }

    public void pack(byte[] bytes) {
        data = bytes;
    }

    public byte[] getPacket() {
        return data;
    }
}
