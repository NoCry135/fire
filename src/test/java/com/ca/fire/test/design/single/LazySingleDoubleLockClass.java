package com.ca.fire.test.design.single;

public class LazySingleDoubleLockClass {

    private LazySingleDoubleLockClass() {

    }


    private static LazySingleDoubleLockClass singleDoubleLockClass = null;

    public static LazySingleDoubleLockClass getSingleDoubleLockClass() {
        if (singleDoubleLockClass == null) {
            synchronized (LazySingleDoubleLockClass.class) {
                if (singleDoubleLockClass == null) {
                    singleDoubleLockClass = new LazySingleDoubleLockClass();
                }
            }
        }
        return singleDoubleLockClass;
    }
}
