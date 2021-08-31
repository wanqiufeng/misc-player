package com.niceshot.jdk.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class StreamCollectorTest {

    @Test
    public void test() {
        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");

        HashMap<String, List<String>> abc = listWithDuplicates.stream().collect(HashMap::new, (hashMap, s) -> hashMap.put(s, Lists.newArrayList("abc")), Map::putAll);
    }



}
