package com.tc.json.syntax;

import com.tc.json.Parse;
import com.tc.json.node.Node;

public class SwitchAnalyse extends  AbstractAnalyse {

    private final static String SWITCH_NODE_CASE = "case";
    private final static String SWITCH_NODE_DATA = "data";
    private final static String CASE_NODE_DATA = "data";
    private final static String CASE_NODE_KEY = "key";
    private final static String CASE_NODE_VALUE = "value";
    private final static String SWITCH_NODE_DEFAULT = "default";

    @Override
    public Node analyseObject(String nodeName, Object template, Object data,Parse parse) {
//        // switch值
//        Object value = this.getJsonValue(data, template.getString(SWITCH_NODE_CASE), firstKey);
//        // case列表
//        JSONArray switchDataArray = template.getJSONArray(SWITCH_NODE_DATA);
//        if (switchDataArray != null && switchDataArray.size() > 0) {
//            for (int i = 0; i < switchDataArray.size(); i++) {
//                JSONObject switchData = switchDataArray.getJSONObject(i);
//                // case值
//                Object caseData = switchData.get(CASE_NODE_DATA);
//                if (Objects.equals(caseData, value)) {
//                    // 返回case值
//                    String key = switchData.getString(CASE_NODE_KEY);
//                    Object caseValue = switchData.get(CASE_NODE_VALUE);
//                    if (this.isReplaceString(key)) {
//                        Object keyObj = this.getJsonValue(data, key, firstKey);
//                        key = String.valueOf(keyObj);
//                    }
//                    return new Node(key, this.parse(caseValue, data, firstKey));
//                }
//            }
//        }
//        // 如果什么的循环没有返回，执行defalut
//        JSONObject defaultJson = template.getJSONObject(SWITCH_NODE_DEFAULT);
//        if (defaultJson != null && defaultJson.containsKey(CASE_NODE_KEY) && defaultJson.containsKey(CASE_NODE_VALUE)) {
//            String key = defaultJson.getString(CASE_NODE_KEY);
//            Object caseValue = defaultJson.get(CASE_NODE_VALUE);
//            if (this.isReplaceString(key)) {
//                Object keyObj = this.getJsonValue(data, key, firstKey);
//                key = String.valueOf(keyObj);
//            }
//            return new Node(key, this.parse(caseValue, data, firstKey));
//        }
        // 都没有返回空
        return null;
    }


}
