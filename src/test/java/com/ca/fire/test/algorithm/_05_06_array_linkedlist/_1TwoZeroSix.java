package com.ca.fire.test.algorithm._05_06_array_linkedlist;


import com.ca.fire.test.algorithm.util.ListNode;
import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 反转链表
 */

public class _1TwoZeroSix {
    /**
     * 方法一：迭代
     * <p>
     * 假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。
     * <p>
     * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。
     * 由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。在更改引用之前，还需要另一个指针来存储下一个节点。
     * 不要忘记在最后返回新的头引用！
     * <p>
     * 时间复杂度：O(n)，假设 nn 是列表的长度，时间复杂度是 O(n)O(n)。
     * 空间复杂度：O(1)。
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    /**
     * 递归版本稍微复杂一些，其关键在于反向工作。假设列表的其余部分已经被反转，现在我该如何反转它前面的部分？
     * <p>
     * 假设列表为：
     * n1->...nk-1->nk -> nk+1 -> ...nm -> null
     * <p>
     * 若从nk+1到nm已经反转，我们正处于nk。
     * n1->...nk-1->nk -> nk+1 <-...nm
     * <p>
     * 我们希望nk+1指向nk
     * 即nk.next.next = nk;
     * ​
     * 要小心n1的下一个必须指向 null。如果你忽略了这一点，你的链表中可能会产生循环。
     * 如果使用大小为 2 的链表测试代码，则可能会捕获此错误。
     * <p>
     * <p>
     * 时间复杂度：O(n)，假设 nn 是列表的长度，那么时间复杂度为 O(n)O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 nn 层。
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    @Test
    public void test02() {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes(nums);
    }

    public void moveZeroes3(int[] nums) {
        int zeroIndex = 0;
        for (int nonZeroIndex = 1; nonZeroIndex < nums.length; nonZeroIndex++) {
            if (nums[zeroIndex] == 0) {
                if (nums[nonZeroIndex] != 0) { // nonZeroIndex所指元素非0
                    exchange(nums, zeroIndex, nonZeroIndex);
                    zeroIndex++;
                }
                // 只是zeroIndex所指元素为0， 但nonZeroIndex所指的也是0，外循环使得nonZeroIndex++去找下一个非0元素
            } else { // (nums[zeroIndex] ！= 0的情况，不需要交换，此时zeroIndex++向右移动去找0元素，外循环使得nonZeroIndex++去找下一个与0交换的元素
                zeroIndex++;
            }
        }
    }


    public void moveZeroes(int[] nums) {
        int start = 0;
        for (int i = 0; i < nums.length && nums[i] != 0; i++)
            start++;


        int d = 0; // 用来表示连续的0的个数
        for (int i = start; i < nums.length; i++) {
            int j = i + d;
            for (; j < nums.length && nums[j] == 0; j++)
                d++;
            if (j >= nums.length)
                return;
            exchange(nums, i, j);
        }
    }

    private void exchange(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    public void moveZeroes1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                //i 后边的移动

                int j = i;
                for (; j < nums.length - i - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                nums[j] = 0;
            }
        }
    }

    @Test
    public void testfindMedianSortedArrays() {
        int[] A = new int[]{1, 2};
        int[] B = new int[]{3, 4};
        double medianSortedArrays = findMedianSortedArrays(A, B);
        System.out.println(medianSortedArrays);
    }

    public double findMedianSortedArrays(int[] A, int[] B) {
        int a1 = A.length;
        int b1 = B.length;
        int[] arr = new int[a1 + b1];
        int len = 0;
        int i = 0;//遍历A
        int j = 0;//遍历B
        while (i < a1 || j < b1) {
            //a1 等于0说明A 数据遍历完了，直接处理B就可以
            if (i == a1) {
                arr[len++] = B[j++];
                continue;
            }
            if (j == b1) {
                arr[len++] = A[i++];
                continue;
            }

            //A的元素小。新数组记录A的值
            if (A[i] < B[j]) {
                arr[len++] = A[i++];
            } else if (A[i] > B[j]) {
                arr[len++] = B[j++];
            } else {
                //记录两个
                arr[len++] = A[i++];
                arr[len++] = B[j++];
            }
        }
        if (arr.length % 2 == 0) {
            return (arr[len / 2] + arr[len / 2 - 1]) / 2.0;
        } else {
            return arr[len / 2];
        }

    }

    class Solution {
        public int maxArea(int[] height) {
            int i = 0, j = height.length - 1, res = 0;
            while (i < j) {
                res = height[i] < height[j] ?
                        Math.max(res, (j - i) * height[i++]) :
                        Math.max(res, (j - i) * height[j--]);
            }
            return res;
        }
    }


}

class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
}
