package com.ca.fire.test.design.composite.unit01;

import java.util.Enumeration;
import java.util.Vector;

public class TreeNode {

    private String name;

    private TreeNode parent;

    public TreeNode(String name) {
        this.name = name;
    }

    private Vector<TreeNode> children = new Vector<TreeNode>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

//    public Vector<TreeNode> getChildren() {
//        return children;
//    }
//
//    public void setChildren(Vector<TreeNode> children) {
//        this.children = children;
//    }

    /**
     * 添加节点
     *
     * @param treeNode
     */
    public void add(TreeNode treeNode) {
        children.add(treeNode);
    }


    public void remove(TreeNode node) {
        children.remove(node);
    }

    /**
     * 获取子节点
     *
     * @return
     */
    public Enumeration<TreeNode> getChildren() {
        return children.elements();
    }
}
