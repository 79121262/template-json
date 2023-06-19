package com.tc.json.node;


import java.util.Collection;


public class ArrayNode extends NodeComponent {
    private int key;

    private Object value;

    public ArrayNode(){}

    public ArrayNode(int index, Object value) {
        this.key = index;
        this.value = value;
    }


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void print(Collection resultJson) {
        resultJson.add(value);
    }


}
