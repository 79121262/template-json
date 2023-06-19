package com.tc.json.node;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Nodes extends NodeComponent {

    private ArrayList nodeComponent = new ArrayList();

    private String key;

    private Object value;

    public Nodes(){}

    public Nodes(String key, Object value) {
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

    public void add(NodeComponent menuComponent) {
        nodeComponent.add(menuComponent);
    }

    public void remove(NodeComponent menuComponent) {
        nodeComponent.remove(menuComponent);
    }

    public NodeComponent getChild(int i) {
        return (NodeComponent) nodeComponent.get(i);
    }

    @Override
    public void print(Map resultJson) {
        Iterator iterator = nodeComponent.iterator();
        while (iterator.hasNext()) {
            NodeComponent menuComponent = (NodeComponent) iterator.next();
            menuComponent.print(resultJson);
        }
    }

    public Iterator createIterator() {

        return  nodeComponent.iterator();
    }
}
