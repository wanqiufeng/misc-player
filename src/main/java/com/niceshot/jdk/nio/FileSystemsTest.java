package com.niceshot.jdk.nio;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class FileSystemsTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/data/test.zip");
        //Files.createDirectories(path.getParent());
        System.out.println("test1:"+path.toUri().getPath());
        URI uri = URI.create("jar:file:"+path.toUri().getPath());
        System.out.println("test2:"+uri.getPath());
        Map<String, String> env = Maps.newHashMap();
        env.put("create","true");
        try(FileSystem fileSystem = FileSystems.newFileSystem(uri, env)) {
            Path path1 = fileSystem.getPath("2022");
            System.out.println("test3:"+path1.toUri().getPath());
            Path directories = Files.createDirectories(path1);
            System.out.println("test4:"+directories.toUri().getPath());
        }
        ;

    }

    public void test() {

    }
}
