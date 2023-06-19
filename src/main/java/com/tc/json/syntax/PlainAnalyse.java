package com.tc.json.syntax;

import com.tc.json.Parse;
import com.tc.json.node.ArrayNode;
import com.tc.json.node.NodeComponent;
import com.tc.json.node.Nodes;

/**
 * key 不进行转换
 */
public class PlainAnalyse extends  AbstractAnalyse {

    public NodeComponent analyseArray(Integer nodeName, Object template, Object data,Parse parse) {
        return new ArrayNode(nodeName, parse.doParse(template, data));
    }

    public NodeComponent analyseObject(String nodeName, Object template, Object data,Parse parse) {
        return new Nodes(nodeName, parse.doParse(template, data));
    }
}
