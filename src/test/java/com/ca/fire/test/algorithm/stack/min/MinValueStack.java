package com.ca.fire.test.algorithm.stack.min;

import java.util.Stack;

/**
 * 获取栈的最小值,
 */
public class MinValueStack {

    private Stack<Integer> dataStack;

    private Stack<Integer> minStack;

    public MinValueStack() {
        this.dataStack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }

    public void push(Integer newValue) {
        if (this.minStack.empty()) {
            minStack.push(newValue);
        } else if (newValue <= this.getMin()) {
            minStack.push(newValue);
        }
        dataStack.push(newValue);
    }

    public Integer pop() {
        if (this.dataStack.empty()) {
            throw new RuntimeException("stack is empty");
        }
        Integer value = dataStack.pop();
        if (value.equals(getMin())) {
            minStack.pop();
        }
        return value;
    }

    public Integer getMin() {
        if (minStack.empty()) {
            throw new RuntimeException("最新值为空");
        }
        return minStack.peek();
    }
}
