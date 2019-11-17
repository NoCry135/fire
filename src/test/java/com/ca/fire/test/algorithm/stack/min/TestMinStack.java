package com.ca.fire.test.algorithm.stack.min;

public class TestMinStack {
    public static void main(String[] args) {
        MinValueStack stack = new MinValueStack();
        stack.push(2);
        stack.push(1);
        stack.push(4);
        stack.push(6);
        stack.push(7);

        System.out.println(stack.getMin());
        Integer pop = stack.pop();
        System.out.println(pop);
        System.out.println(stack.getMin());

        MinValueStack minValueStack = new MinValueStack();
        minValueStack.push(2);
        minValueStack.push(1);
        minValueStack.push(4);
        minValueStack.push(0);
        minValueStack.push(7);
        System.out.println(minValueStack.getMin());

    }
}
