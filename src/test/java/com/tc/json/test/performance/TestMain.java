package com.tc.json.test.performance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.json.Parse;
import com.tc.json.extract.DefaultExtractor;
import com.tc.json.test.jsckson.ArrayTest;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author tc
 * @date 2023/6/21
 * @email tiancai@citycloud.com.cn
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
        ClassLoader loader = ArrayTest.class.getClassLoader();
        byte[] s = Files.readAllBytes(Paths.get(loader.getResource("json/array/json4.json").toURI()));
        //byte[] s = Files.readAllBytes(Paths.get(loader.getResource("json/json4.json").toURI()));
        byte[] s2 = Files.readAllBytes(Paths.get(loader.getResource("json/array/json5.json").toURI()));
        //dbyte[] s2 = Files.readAllBytes(Paths.get(loader.getResource("json/json5.json").toURI()));
        long l = System.currentTimeMillis();
        for (int i =0;  i<100000;i++){
            long l2= System.currentTimeMillis();
            ObjectMapper mapper = new ObjectMapper();
            Object tempJson = mapper.readValue(s,Object.class);
            Object root = mapper.readValue(s2,Object.class);
            Parse parse = new Parse(new DefaultExtractor(root));
            Object data = parse.parse(tempJson, root);

            //System.out.println((System.currentTimeMillis()-l2));
            //System.out.println(mapper.writeValueAsString(data));
        }
        System.out.println((System.currentTimeMillis()-l)/1000);
    }
}
