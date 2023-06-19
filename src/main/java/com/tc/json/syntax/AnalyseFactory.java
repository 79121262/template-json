package com.tc.json.syntax;

import java.util.HashMap;
import java.util.Map;

public class AnalyseFactory {
    private final static String IF_NODE_NAME = "x-if";
    private final static String DEFAULT = "DEFAULT";
    private final static String PLAIN = "PLAIN";

    private final static String SWITCH_NODE_NAME = "x-switch";
    private final static String LIST_NODE_NAME = "x-list";
    private final static String DECONSTRUCTION_NODE_NAME = "...";

    //保留字用于数组类型的模板
    private final static String RESERVED = "reserved";


    public static AbstractAnalyse getInstance(Object nodeName, Object tpl) {
        //String key 不进行转换
        if (nodeName instanceof String) { //对象
            String nodeNames = nodeName.toString();
            if (nodeNames.startsWith(DECONSTRUCTION_NODE_NAME)) {
                return analyse.get(DECONSTRUCTION_NODE_NAME);
            }
            if (tpl instanceof String) {
                return analyse.get(PLAIN);
            }
            return analyse.containsKey(nodeNames) ? analyse.get(nodeNames) : analyse.get(DEFAULT);
        } else if (nodeName instanceof Integer) { //数组
            if (tpl instanceof Map) {
                Map it = (Map) tpl;
                return it.containsKey(RESERVED) ? analyse.get(it.get(RESERVED)) : analyse.get(DEFAULT);
            }
            if (tpl instanceof String) {
                if(((String) tpl).startsWith(DECONSTRUCTION_NODE_NAME)){
                    return analyse.get(DECONSTRUCTION_NODE_NAME);
                }
                return analyse.get(PLAIN);
            }
        }
        return analyse.get(DEFAULT);
    }

    private final static Map<String, AbstractAnalyse> analyse = new HashMap<>();

    static {
        analyse.put(IF_NODE_NAME, new IfAnalyse());
        analyse.put(LIST_NODE_NAME, new ListAnalyse());
        analyse.put(DECONSTRUCTION_NODE_NAME, new DeconstructionAnalyse());

        analyse.put(SWITCH_NODE_NAME, new SwitchAnalyse());

        analyse.put(PLAIN, new PlainAnalyse());
        analyse.put(DEFAULT, new DefaultAnalyse());

    }


}
