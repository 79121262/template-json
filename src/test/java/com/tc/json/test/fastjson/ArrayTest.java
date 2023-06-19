package com.tc.json.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.tc.json.Parse;
import com.tc.json.extract.DefaultExtractor;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author tc
 * @date 2023/6/17
 * @email tiancai@citycloud.com.cn
 */
public class ArrayTest {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ArrayTest.class.getClassLoader();
        byte[] s = Files.readAllBytes(Paths.get(loader.getResource("json/array/json4.json").toURI()));
        //byte[] s = Files.readAllBytes(Paths.get(loader.getResource("json/json4.json").toURI()));
        byte[] s2 = Files.readAllBytes(Paths.get(loader.getResource("json/array/json5.json").toURI()));
        //dbyte[] s2 = Files.readAllBytes(Paths.get(loader.getResource("json/json5.json").toURI()));
        Object tempJson = JSON.parse(new String(s));
        Object root = JSON.parse(new String(s2));
        Parse parse = new Parse(new DefaultExtractor(root));
        Object data = parse.parse(tempJson, root);

        System.out.println(JSON.toJSONString(data));

        //System.out.println(JSON.toJSONString("ABC"));
    }
}
