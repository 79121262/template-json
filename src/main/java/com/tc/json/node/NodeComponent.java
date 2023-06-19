package com.tc.json.node;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


public abstract class NodeComponent {
    public void add(NodeComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(NodeComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void print(Map resultJson) {
        throw new UnsupportedOperationException();
    }
    public void print(Collection resultJson) {
        throw new UnsupportedOperationException();
    }

    public Iterator createIterator() {
        throw new UnsupportedOperationException();
    }

}
