package com.ca.fire.test.design.command.unit03;

import java.util.ArrayList;
import java.util.List;

public class producer {

    public static List produceRequests() {
        List queue = new ArrayList();
        queue.add(new Engineer());
        queue.add(new Politician());
        queue.add(new Programmer());
        return queue;
    }
}
