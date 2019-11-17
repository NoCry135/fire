package com.ca.fire.test.design.state.unit01;

/**
 * 1.通过状态改变获得不同的行为
 * 2.他人可以看到你的变化:他人的方法里包含了对你的参数的判断,来调用你的方法
 */
public class TestState {

    public static void main(String[] args) {
        State state = new State();

        Context context = new Context(state);

        state.setValue("hi");

        context.speak();
        state.setValue("hello");

        context.speak();

//        state.setValue("您好");
//
//        context.speak();

    }
}
