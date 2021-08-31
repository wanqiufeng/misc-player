package com.niceshot.jdk.datastructure;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

public class ArrayListTest {
    @Test
    public void test() {
        ArrayList<String> objects = Lists.newArrayList();
        objects.add("a");
        objects.add(null);
        objects.add("b");
        String join = String.join(",", objects);
        System.out.println(join);
    }
}
