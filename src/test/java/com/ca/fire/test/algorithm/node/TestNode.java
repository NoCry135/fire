package com.ca.fire.test.algorithm.node;

import com.ca.fire.test.algorithm.util.ListNode;
import org.junit.Test;

public class TestNode {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    @Test
    public void test01() {
        int[] nums = new int[]{1, 2, 1,3,2,1};
        removeDuplicates(nums);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addHead(listNode1);
        singleLinkedList.addHead(listNode2);
        singleLinkedList.addHead(listNode3);
        System.out.println(singleLinkedList);


    }

    @Test
    public void test02() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();

        singleLinkedList1.addTail(listNode1);
        singleLinkedList1.addTail(listNode2);
        singleLinkedList1.addTail(listNode3);

        System.out.println(singleLinkedList1);

    }

    @Test
    public void test03() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.addTail(listNode1);
        singleLinkedList.addTail(listNode2);
        singleLinkedList.addTail(listNode3);

        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(3);
        ListNode listNode13 = new ListNode(4);

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();

        singleLinkedList1.addTail(listNode11);
        singleLinkedList1.addTail(listNode12);
        singleLinkedList1.addTail(listNode13);
        singleLinkedList.print();
        singleLinkedList1.print();
        ListNode merge = getMerge(singleLinkedList.getHead(), singleLinkedList1.getHead());
        System.out.println(merge);

    }

    public ListNode getMerge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = getMerge(l1.next, l2);
        } else {
            head = l2;
            head.next = getMerge(l1, l2.next);
        }
        return head;
    }
}
