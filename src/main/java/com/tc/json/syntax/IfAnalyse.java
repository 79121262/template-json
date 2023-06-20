package com.tc.json.syntax;

import com.tc.json.Parse;
import com.tc.json.exception.JsonTplException;
import com.tc.json.node.ArrayNode;
import com.tc.json.node.Node;
import com.tc.json.node.NodeComponent;
import com.tc.json.utils.StringUtils;

import java.util.Map;

/**
 * 对象：
 *     {
 *       "x-if": {
 *         "expression": "1==1",
 *         "key": "data12",
 *         "value": {
 *           "fdsss": "${data.code}"
 *         }
 *       }
 *     }
 * 数组：
 *   [{
 *     "reserved":"x-if",
 *     "expression": "1=1",
 *     "value": {
 *       "success": "${data.success}",
 *     }
 *   }]
 */

public class IfAnalyse extends  AbstractAnalyse {
    private final static String IF_NODE_EXPRESSION = "expression";
    private final static String IF_NODE_KEY = "key";
    private final static String IF_NODE_VALUE = "value";


    public NodeComponent analyseArray(Integer nodeName, Object tpl, Object d, Parse parse) {
        Map template = (Map) tpl;
        Object ifItem = template.get(IF_NODE_VALUE);
        return new ArrayNode(nodeName,parse.doParse(ifItem, d));
    }

    @Override
    public Node analyseObject(String nodeName, Object tpl, Object d,Parse parse) {
        Map template = (Map)tpl;
        String expression = (String) template.get(IF_NODE_EXPRESSION);
        if (!testBoolean(expression, d)) {
            return null;
        }
        Object ifItem = template.get(IF_NODE_VALUE);
        String key = (String)template.get(IF_NODE_KEY);
        if (StringUtils.isBlank(key)) {
            key = nodeName;
        }
        if (ifItem == null) {
            throw new JsonTplException(template + "中" + IF_NODE_VALUE + "值不能为空");
        }
        return new Node(key, parse.doParse(ifItem, d));
    }


    private boolean testBoolean(String expression, Object data) {
        return true;
    }
}
