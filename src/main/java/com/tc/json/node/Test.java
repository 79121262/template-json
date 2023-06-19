package com.tc.json.node;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        Node node = new Node("aaa","bbb");
        Node node2 = new Node("aaa2","bbb2");
        Nodes nodes = new Nodes("菜单","菜单");
        nodes.add(node);
        nodes.add(node2);
        Nodes nodes1 = new Nodes("菜单1","菜单2");
        nodes1.add(node2);
        nodes.add(nodes1);

        Iterator iterator = nodes.createIterator();


        while (iterator.hasNext()) {//遍历组合内的每一个元素
            NodeComponent menuComponent = (NodeComponent) iterator.next();
            try {
                JSONObject jsonObject = new JSONObject();
                menuComponent.print(jsonObject);
                System.out.println("JSON.toJSONString(jsonObject) = " + JSON.toJSONString(jsonObject));
            } catch (UnsupportedOperationException e) {//我们在菜单(Menu上并没有覆盖父类的isVegetain()方法，所以永远都会抛出异常)
            }
        }
    }
}
