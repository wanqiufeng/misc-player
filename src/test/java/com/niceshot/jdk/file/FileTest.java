package com.niceshot.jdk.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {
    @Test
    public void test() throws IOException {
        File file = new File("C:\\Users\\wanqi\\Temp\\filetest\\test.txt2");
        boolean mkdirs = file.getParentFile().mkdirs();
        file.createNewFile();
    }
}
