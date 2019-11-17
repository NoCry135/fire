package com.ca.fire.test.event;

import java.util.EventObject;

/**
 * 事件类,用于封装事件源及一些与事件相关的参数.
 */
public class CusEvent extends EventObject {

    private Object source;//事件源

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CusEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
