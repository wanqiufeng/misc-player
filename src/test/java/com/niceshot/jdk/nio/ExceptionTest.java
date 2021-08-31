package com.niceshot.jdk.nio;

import org.junit.Test;

public class ExceptionTest {
    @Test
    public void test(){
        try {
            System.out.println(343);
            throw new RuntimeException();
        } catch (Exception e) {
           throw new RuntimeException(e);
        } finally {
            System.out.println("hello world");
        }
    }
}
