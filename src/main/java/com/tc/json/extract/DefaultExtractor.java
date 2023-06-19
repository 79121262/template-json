package com.tc.json.extract;


import java.util.List;
import java.util.Map;

public  class DefaultExtractor extends AbstractJsonExtractor {


    public DefaultExtractor(Object root) {
        super(root);
    }

    public Object getJsonValue(Object json, String[] split) {
        if (split == null) {
            return null;
        }
        Object temp = json;
        for (String field : split) {
            if (!field.contains(LEFT_ARRAY_TAG)) {
                //对象
                Map object = (Map) temp;
                temp = object.get(field);
                continue;
            }
            //数组
            String[] array = field.split(RIGHT_ARRAY_TAG);
            for (String s : array) {
                if (s == null) {
                    continue;
                }
                if (s.contains(LEFT_ARRAY_TAG)) {
                    s = s.substring(0, s.length() - 1);
                    List jsonArray = (List) temp;
                    temp = jsonArray.get(Integer.valueOf(s));
                } else if(s!=null && s.length()!=0) {
                    Map object = (Map) temp;
                    temp = object.get(s);
                }
            }

        }
        return temp;
    }
}
