package com.ca.fire.test.design.composite.unit01;

public class TreeTest {

    private TreeNode root;

    public TreeTest(String node) {
        this.root = new TreeNode(node);
    }

    public static void main(String[] args) {
        TreeTest tree = new TreeTest("A");


        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");

        b.add(c);
        tree.root.add(b);
        System.out.println("build the tree finished!");

    }
}
