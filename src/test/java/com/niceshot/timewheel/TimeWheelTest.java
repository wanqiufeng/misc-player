package com.niceshot.timewheel;

import org.junit.Test;

public class TimeWheelTest {
    @Test
    public void test() throws InterruptedException {
        Object lock = new Object();
        synchronized (lock) {
            lock.wait(5000);
        }

    }
}
