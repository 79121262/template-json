package com.tc.json.syntax;

import com.tc.json.Parse;
import com.tc.json.node.NodeComponent;

/**
 * @author tc
 * Date 2023/6/17
 * Email 79121262@qq.com
 */
public class AbstractAnalyse {

    protected final static String ROOT_KEY = "data";


    public NodeComponent analyse(Object nodeName, Object template, Object data, Parse p) {
        if (nodeName instanceof String) {
            return analyseObject((String) nodeName, template, data, p);
        }
        if (nodeName instanceof Integer) {
            return analyseArray((Integer) nodeName, template, data, p);
        }
        return null;
    }

    public NodeComponent analyseObject(String nodeName, Object template, Object data, Parse parse) {
        throw new UnsupportedOperationException();
    }

    public NodeComponent analyseArray(Integer nodeName, Object template, Object data, Parse parse) {
        throw new UnsupportedOperationException();
    }


}
