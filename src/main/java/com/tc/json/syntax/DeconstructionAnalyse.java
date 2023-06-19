package com.tc.json.syntax;

import com.tc.json.Parse;
import com.tc.json.node.ArraysNode;
import com.tc.json.node.Node;
import com.tc.json.node.NodeComponent;
import com.tc.json.node.Nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 对象：
 * {"...data.parentBank": "..."}
 *
 * 数组：
 * ["...data.data"]
 */
public class DeconstructionAnalyse extends AbstractAnalyse {

    @Override
    public NodeComponent analyseObject(String nodeName, Object template, Object data, Parse parse) {
        nodeName = nodeName.substring(3);
        Object jsonValue = parse.parseValue(data, nodeName);
        if (!(jsonValue instanceof Map)) {
            return new Nodes(nodeName, "");
        }
        Map json = (Map) jsonValue;
        Set<String> keys = json.keySet();
        Nodes parent = new Nodes("", "");
        for (String itemKey : keys) {
            Node node = new Node(itemKey, json.get(itemKey));
            parent.add(node);
        }
        return parent;
    }

    @Override
    public NodeComponent analyseArray(Integer i, Object template, Object data, Parse parse) {
        String nodeName = (String) template;
        nodeName = nodeName.substring(3);
        Object jsonValue = parse.parseValue(data, nodeName);
        if (jsonValue instanceof Map) {
            Map json = (Map) jsonValue;
            Set<String> keys = json.keySet();
            Collection parent = new ArrayList<>();
            for (String itemKey : keys) {
                parent.add(json.get(itemKey));
            }
            return new ArraysNode(0, parent);
        } else if (jsonValue instanceof Collection) {
            Collection json = (Collection) jsonValue;
            return new ArraysNode(0, json);
        }
        return null;
    }
}
