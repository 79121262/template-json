package com.tc.json.convert;

import com.tc.json.extract.AbstractJsonExtractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertFactory {

    //从() 中取值
    private static Pattern p = Pattern.compile("(?<=\\()(.+?)(?=\\))");

    //从 [] 中取值
    private static Pattern param = Pattern.compile("(?<=\\[)(.+?)(?=\\])");

    public static Convert getInstance(String s, AbstractJsonExtractor extractor) {
        Convert convert = create(s, extractor);
        if (convert == null)
            return new NormalConvert();
        return convert;
    }

    public static Convert create(String s, AbstractJsonExtractor extractor) {
        String function = getFunction(s);

        if (function == null) {
            return null;
        }

        if (function.startsWith("dateformate")) {
            String param1 = getParam(function);
            String[] split = param1.split(",");
            if (split.length < 2) {
                return null;
            }
            return new DateConvert(split[0], split[1]);
        }
        return null;
    }


    private static String getFunction(String s) {
        Matcher m = p.matcher(s);
        while (m.find()) {
            String group = m.group();
            return group;
        }
        return null;
    }

    private static String getParam(String s) {
        Matcher m = param.matcher(s);
        while (m.find()) {
            String group = m.group();
            return group;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getParam(getFunction(" ${data[0].abac}(dateformate[YYYY-MMM-DD,YYYY-MMM])")));
    }

}
