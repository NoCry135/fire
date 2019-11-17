package com.ca.fire.test.algorithm.twotree;

import java.util.Stack;

public class NodeStack {
    private String value;
    private NodeStack left;
    private NodeStack right;

    public NodeStack(String value) {
        this.value = value;
    }

    public void preOrderUnRecur(NodeStack head) {
        System.out.println("pre-order: ");
        if (head != null) {
            Stack<NodeStack> nodeStacks = new Stack<>();
            nodeStacks.add(head);
            while (!nodeStacks.empty()) {
                head = nodeStacks.pop();
                System.out.println(head.value + " ");
                if (head.right != null) {
                    nodeStacks.push(head.right);
                }
                if (head.left != null) {
                    nodeStacks.push(head.left);
                }
            }
        }
        System.out.println();
    }

    public void inOrderUnRecur(NodeStack head) {
        System.out.println("in-order: ");
        if (head != null) {
            Stack<NodeStack> nodeStacks = new Stack<>();
            nodeStacks.add(head);
            while (!nodeStacks.empty() || head != null) {

                if (head != null) {
                    nodeStacks.push(head);
                    head = head.left;
                } else {
                    head = nodeStacks.pop();
                    System.out.println(head.value + " ");

                    head = head.right;
                }
            }
        }
        System.out.println();
    }


    public void posOrderUnRecurl(NodeStack head) {
        System.out.println("pos-order: ");
        if (head != null) {
            Stack<NodeStack> s1 = new Stack<>();
            Stack<NodeStack> s2 = new Stack<>();
            s1.push(head);
            while (!s1.empty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            while (!s2.empty()) {
                System.out.println(s2.pop().value + " ");
            }
        }
        System.out.println();

    }

    public void posOrderUnRecurl2(NodeStack head) {
        System.out.println("pos-order: ");
        if (head != null) {
            Stack<NodeStack> stacks = new Stack<>();
            stacks.push(head);
            NodeStack c = null;
            while (!stacks.isEmpty()) {
            }

        }
        System.out.println();

    }
}
