package com.feiche.driver.socket_test.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xian on 2015/11/12.
 */
public final class AtomicIntegerUtil {

    private static final AtomicInteger mAtomicInteger = new AtomicInteger();

    public static int getIncrementID() {
        return mAtomicInteger.getAndIncrement();
    }
}
