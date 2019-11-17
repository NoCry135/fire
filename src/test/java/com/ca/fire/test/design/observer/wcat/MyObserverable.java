package com.ca.fire.test.design.observer.wcat;

public interface MyObserverable {

    void registerObserver(MyObserver o);

    void removeObserver(MyObserver o);

    void notifyObserver();
}
