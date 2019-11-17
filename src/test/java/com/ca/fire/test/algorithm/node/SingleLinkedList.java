package com.ca.fire.test.algorithm.node;

import com.ca.fire.test.algorithm.util.ListNode;

public class SingleLinkedList {

    //链表节点的个数
    private int size;

    //头节点
    private ListNode head;

    public SingleLinkedList() {
        size = 0;
        head = null;
    }

    //在链表头添加元素
    public ListNode addHead(ListNode obj) {
        ListNode newNode = obj;
        if (size == 0) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
        return newNode;
    }


    public ListNode addTail(ListNode node) {
        ListNode newNode = node;
        if (size == 0) {
            head = newNode;
        } else {
            ListNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;

        }
        size++;
        return newNode;
    }

    //在链表头删除元素
    public Object deleteHead() {
        Object obj = head.val;
        head = head.next;
        size--;
        return obj;
    }

    public void print() {
        ListNode tmp = head;
        while (tmp != null){
            System.out.println("tmp.data:" + tmp.val);
            tmp = tmp.next;
        }

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ListNode getHead() {
        return head;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }
}
