package com.ca.fire.jvmMonitor.profiler.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMultiSet<E> {
    private final transient ConcurrentMap<E, AtomicInteger> counterMap;

    public ConcurrentHashMultiSet() {
        this.counterMap = new ConcurrentHashMap<E, AtomicInteger>();
    }

    public int add(final E element) {
        if (element == null) {
            return 0;
        }
        AtomicInteger existingCounter = this.counterMap.get(element);
        if (existingCounter == null) {
            final AtomicInteger newCounter = new AtomicInteger();
            existingCounter = this.counterMap.putIfAbsent(element, newCounter);
            if (existingCounter == null) {
                existingCounter = newCounter;
            }
        }
        return existingCounter.incrementAndGet();
    }

    public Set<E> elementSet() {
        return this.counterMap.keySet();
    }

    public int count(final E element) {
        if (element == null) {
            return 0;
        }
        final AtomicInteger existingCounter = this.counterMap.get(element);
        return (existingCounter == null) ? 0 : existingCounter.get();
    }

}
