package com.tc.json.syntax;

import com.tc.json.Parse;
import com.tc.json.node.ArrayNode;
import com.tc.json.node.Node;
import com.tc.json.node.NodeComponent;

public class DefaultAnalyse extends  AbstractAnalyse {
    @Override
    public NodeComponent analyseObject(String nodeName, Object template, Object data, Parse parse) {
        if (template == null) {
            return new Node(nodeName, "");
        }
        nodeName = parse.parseStringValue(data,nodeName);
        return new Node(nodeName, parse.doParse(template, data));
    }

    @Override
    public NodeComponent analyseArray(Integer nodeName, Object tpl, Object data, Parse parse) {
        return new ArrayNode(nodeName,parse.doParse(tpl, data));
    }
}
