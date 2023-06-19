package com.tc.json.extract;

import com.tc.json.utils.StringUtils;

import java.util.Objects;

public abstract class AbstractJsonExtractor {
    protected Object root = null;
    protected final static String ROOT_KEY = "data";
    public final static String LEFT_OBJECT_TAG = "\\$\\{";
    public final static String RIGHT_OBJECT_TAG = "}";
    public final static String LEFT_ARRAY_TAG = "]";
    public final static String RIGHT_ARRAY_TAG = "\\[";

    public AbstractJsonExtractor(Object root) {
        this.root = root;
    }

    public abstract Object getJsonValue(Object json,String[] split);

    public Object takeObjectByJson(Object json, String keyStr) {
        if (StringUtils.isBlank(keyStr)) {
            return "";
        }
        //string 类型不做处理
        if (json instanceof String) {
            return json;
        }
        String expressionString = getExpressionString(keyStr);
        boolean ifRoot = isRoot(expressionString);
        return getJsonValue(ifRoot ? root : json, expressionString);
    }

    public String getExpressionString(String text) {
        String[] split = text.split(RIGHT_OBJECT_TAG);
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(LEFT_OBJECT_TAG);
            if (split1.length < 2) {
                continue;
            }
            return split1[1];
        }
        return text;
    }


    public boolean isRoot(String keyStr) {
        String[] keyArray = keyStr.split("\\.");
        String key = keyArray[0];
        return Objects.equals(key, ROOT_KEY) || key.startsWith(ROOT_KEY + "[");
    }


    /**
     * 获得json内容
     * @return Object
     */
    public Object getJsonValue(Object jsonValue, String keyStr) {
        String[] keyArray = keyStr.split("\\.");
        //去掉上层节点，因为jsonValue ， 已经属于上层节点key 从下层取值, 删除 data , 或 item
        keyArray = deleteRootKey(keyArray);
        // 从json 中获取值
        return getJsonValue(jsonValue, keyArray);
    }




    private String[] deleteRootKey(String[] keyArray) {
        String s = keyArray[0];
        if(s.contains("[")){
            keyArray[0] = keyArray[0].substring(keyArray[0].indexOf("["));
            return keyArray;
        }else{
            String[] tmp = new String[keyArray.length-1];
            System.arraycopy(keyArray,1,tmp,0,tmp.length);
            return tmp;
        }
    }

    public boolean ifSupport(String text){
        if (StringUtils.isNotBlank(text) && text.trim().startsWith("${") && text.trim().endsWith("}")){
            return true;
        }
        return false;
    }
}
