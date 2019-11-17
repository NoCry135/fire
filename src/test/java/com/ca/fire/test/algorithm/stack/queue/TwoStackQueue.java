package com.ca.fire.test.algorithm.stack.queue;

import java.util.Stack;

/**
 * 利用两个栈实现队列
 * ??这样如果pushStack空了,popstack没有空怎么办,无法重用
 */
public class TwoStackQueue {
    private Stack<Integer> pushStack;

    private Stack<Integer> popStack;


    public TwoStackQueue() {
        this.popStack = new Stack<Integer>();
        this.pushStack = new Stack<Integer>();
    }

    public void add(Integer value) {
        pushStack.push(value);
    }

    public Integer pop() {
        if (pushStack.isEmpty()) {
            throw new RuntimeException("empty stack");
        } else if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    public Integer peek() {
        if (pushStack.isEmpty()) {
            throw new RuntimeException("empty stack");
        } else if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }

}
