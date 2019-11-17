package com.ca.fire.test.algorithm.twotree;

/**
 * 遍历二叉树
 */
public class Node {
    private String value;
    private Node left;
    private Node right;

    public Node(String value) {
        this.value = value;
    }

    public void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + "");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + "");
        inOrderRecur(head.right);
    }

    public void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.value + "");
    }
}
