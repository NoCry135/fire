package com.ca.fire.test.design.decorator.unit01;

public class TestSource {

    public static void main(String[] args) {

        Sourceable sourceable = new Source();

        sourceable.method();
        Sourceable decorator = new Decorator(sourceable);
        decorator.method();

    }
}
