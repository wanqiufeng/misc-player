package com.niceshot.config;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONUtil;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json2YamlUtils {
    public static void main(String[] args) throws YamlException {
        json2Yaml();
    }

    /**
     * 将json转化为yaml格式的工具
     * @throws YamlException
     */
    public static void json2Yaml() throws YamlException {
        FileReader fileReader = new FileReader("config.json");
        Map<String,String> map = JSONUtil.toBean(fileReader.readString(), Map.class);
        Map<String,Object> levelMap = buildLevelMap(map);
        StringWriter stringWriter = new StringWriter();
        YamlWriter writer = new YamlWriter(stringWriter);
        writer.write(levelMap);
        writer.close();
        System.out.println(stringWriter);
    }

    private static Map<String, Object> buildLevelMap(Map<String, String> map) {
        Map<String,Object> result = Maps.newHashMap();
        map.entrySet().forEach(stringStringEntry -> {
            String[] split = stringStringEntry.getKey().split("\\.");
            List<String> keyLists = Lists.newArrayList(split);
            Map<String,Object> iterateMapPoint = result;
            for(int i=0;i<keyLists.size();i++) {
                String currKey = keyLists.get(i);
                if(i== keyLists.size()-1) {
                    iterateMapPoint.put(currKey,stringStringEntry.getValue());
                } else {
                    if(!iterateMapPoint.containsKey(currKey)) {
                        iterateMapPoint.put(currKey,new HashMap<>());
                    }
                    iterateMapPoint = (Map<String, Object>) iterateMapPoint.get(currKey);
                }
            }
        });
        return result;
    }


}
