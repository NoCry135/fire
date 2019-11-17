package com.ca.fire.test.algorithm.node;

/**
 * 打印两个有序链表的公共部分
 */
public class MyNode {
    private int value;
    private MyNode next;

    public MyNode(int value) {
        this.value = value;
    }

    public void printCommonPart(MyNode node1, MyNode node2) {
        System.out.println("Common part: ");
        while (node1 != null && node2 != null) {
            if (node1.value < node2.value) {
                node1 = node1.next;
            } else if (node1.value > node2.value) {
                node2 = node2.next;
            } else {
                System.out.println(node1.value + "");
                node1 = node1.next;
                node2 = node2.next;
            }
        }
    }

    public MyNode merge(MyNode head1, MyNode head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }
        MyNode head = head1.value < head2.value ? head1 : head2;
        MyNode cur1 = head == head1 ? head1 : head2;
        MyNode cur2 = head == head1 ? head2 : head1;
        MyNode pre = null;
        MyNode next = null;

        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }
}
