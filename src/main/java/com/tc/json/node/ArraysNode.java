package com.tc.json.node;


import java.util.ArrayList;
import java.util.Collection;

public class ArraysNode extends NodeComponent {
    private ArrayList nodeComponent = new ArrayList();
    private int key;

    private Object value;

    public ArraysNode(){}

    public ArraysNode(int index, Collection value) {
        this.key = index;
        nodeComponent.addAll(value);
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
        resultJson.addAll(nodeComponent);
    }


}
