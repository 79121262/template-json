package com.tc.json.test.jsckson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.json.Parse;
import com.tc.json.extract.DefaultExtractor;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author tc
 * Date 2023/6/17
 * Email 79121262@qq.com
 */
public class ObjectTest {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ObjectTest.class.getClassLoader();
        byte[] s = Files.readAllBytes(Paths.get(loader.getResource("json/object/json.json").toURI()));
        //byte[] s = Files.readAllBytes(Paths.get(loader.getResource("json/json4.json").toURI()));
        byte[] s2 = Files.readAllBytes(Paths.get(loader.getResource("json/object/beeltSource.json").toURI()));
        //dbyte[] s2 = Files.readAllBytes(Paths.get(loader.getResource("json/json5.json").toURI()));


        ObjectMapper mapper = new ObjectMapper();
        Object tempJson = mapper.readValue(s,Object.class);
        Object root = mapper.readValue(s2,Object.class);
        Parse parse = new Parse(new DefaultExtractor(root));
        Object data = parse.parse(tempJson, root);
        System.out.println(mapper.writeValueAsString(data));

        //System.out.println(JSON.toJSONString("ABC"));
    }
}
