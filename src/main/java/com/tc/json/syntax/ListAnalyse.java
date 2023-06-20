package com.tc.json.syntax;

import com.tc.json.Parse;
import com.tc.json.exception.JsonTplException;
import com.tc.json.node.ArraysNode;
import com.tc.json.node.Node;
import com.tc.json.node.NodeComponent;
import com.tc.json.utils.StringUtils;

import java.util.*;

/**
 * 对象：
 * {
 * "x-list": {
 * "expression": "item as data.childBankList",
 * "key": "list",
 * "value": {
 * "companyName": "${item.bankBranchName}"
 * }
 * }
 * }
 * <p>
 * 数组：
 * [
 * {
 * "reserved":"x-list",
 * "expression": "item as data.data",
 * "value": {
 * "name": "${item.title}",
 * "num": "${item.num}"
 * }
 * }
 * ]
 */
public class ListAnalyse extends AbstractAnalyse {

    private final static String LIST_NODE_EXPRESSION = "expression";
    private final static String LIST_NODE_KEY = "key";
    private final static String LIST_NODE_VALUE = "value";
    private final static String ITEM_AS_DATA = "as";

    public NodeComponent analyseArray(Integer nodeName, Object tpl, Object d, Parse parse) {
        Map template = (Map) tpl;
        Collection objects = getObjects(template, d, parse);
        return new ArraysNode(nodeName, objects);
    }

    @Override
    public Node analyseObject(String nodeName, Object tpl, Object d, Parse parse) {
        Map template = (Map) tpl;
        Collection newArray = getObjects(template, d, parse);
        // list对象的key
        String listKey = (String) template.get(LIST_NODE_KEY);

        if (StringUtils.isBlank(listKey)) {
            listKey = nodeName;
        }

        String ifNodeKey = parse.parseStringValue(d, listKey);
        return new Node(ifNodeKey, newArray);
    }

    private Collection getObjects(Map template, Object data, Parse parse) {
        String listStr = (String) template.get(LIST_NODE_EXPRESSION);
        String[] listAry = listStr.split(ITEM_AS_DATA);
        if (listAry == null || listAry.length != 2) {
            throw new JsonTplException("循环属性设置不正确：" + listStr);
        }
        String itemKeyName = listAry[0].trim(); // 循环子元素名称
        if (Objects.equals(itemKeyName, ROOT_KEY)) {
            throw new JsonTplException("循环子元素不能命名为：" + ROOT_KEY + ", [" + listStr + "]");
        }
        Collection listArray = (Collection) parse.parseValue(data, listAry[1].trim());
        List newArray = new ArrayList();
        Object subTemplate = template.get(LIST_NODE_VALUE);
        if (listArray != null) {
            for (Object o : listArray) {
                //全部
                if (Objects.equals(subTemplate, itemKeyName)) {
                    newArray.add(o);
                } else if (o instanceof Map) {
                    Object o1 = parse.doParse(subTemplate, o);
                    if (o1 instanceof Collection) {
                        newArray.addAll((Collection) o1);
                    } else {
                        newArray.add(o1);
                    }
                }
            }
        }
        return newArray;
    }


}
