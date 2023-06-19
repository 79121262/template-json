package com.tc.json.node;


import java.util.Map;


public class Node extends NodeComponent {
    private String key;

    private Object value;

    public Node(){}

    public Node(String key,Object value) {
        this.key = key;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void print(Map resultJson) {
        resultJson.put(getKey(),getValue());

        //System.out.println(getKey()+getValue());
    }


}
