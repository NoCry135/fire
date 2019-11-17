package com.ca.fire.test.design.adapter.unit03;

/**
 * 类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
 * 对象的适配器模式：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
 * 接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。
 */
public class WrapperTest {
    public static void main(String[] args) {
        Sourceable sourceSub1 = new SourceSub1();
        Sourceable sourceSub2 = new SourceSub2();
        sourceSub1.method1();
        sourceSub1.method2();

        sourceSub2.method1();
        sourceSub2.method2();
    }
}
