package com.tc.json;

import com.tc.json.extract.AbstractJsonExtractor;
import com.tc.json.convert.Convert;
import com.tc.json.convert.ConvertFactory;
import com.tc.json.syntax.AnalyseFactory;

import java.util.*;

public class Parse {
    protected AbstractJsonExtractor extractor = null;

    public Parse(AbstractJsonExtractor extractor) {
        this.extractor = extractor;
    }


    public Object parseValue(Object data, String keyStr) {
        Object o = extractor.takeObjectByJson(data, keyStr);
        Convert instance = ConvertFactory.getInstance(keyStr, extractor);
        return instance.convert(o, keyStr);
    }


    public String parseStringValue(Object data, String keyStr) {
        if (!extractor.ifSupport(keyStr)) {
            return keyStr;
        }
        return String.valueOf(parseValue(data, keyStr));
    }

    /**
     * 解析 template
     *
     * @param template
     * @param data
     * @return
     */
    public Object parse(Object template, Object data) throws Exception {
        try {
            return doParse(template, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public Object doParse(Object template, Object data) {
        if (template == null) {
            return null;
        }
        if (template instanceof Map) {
            Map result = new HashMap();
            Map tpl = (Map) template;
            Set<String> keys = tpl.keySet();
            for (String itemKey : keys) {
                AnalyseFactory.getInstance(itemKey, tpl).analyse(itemKey, tpl.get(itemKey), data, this).print(result);
            }
            return result;
        }

        if (template instanceof Collection) {
            Collection resultArray = new ArrayList();
            Collection tpl = (Collection) template;
            for (Object o : tpl) {
                int i = 0;
                AnalyseFactory.getInstance(++i, o).analyse(++i, o, data, this).print(resultArray);
            }

            return resultArray;
        }
        if (template instanceof String) {
            return parseStringValue(data, (String) template);
        }
        return template;
    }
}
