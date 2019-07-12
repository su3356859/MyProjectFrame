package com.demo.android.event;

/**
 * Created by luowei on 2017/11/8.
 */

public class EventAction {
    private EventType mEventType;//动作类型
    private Object data;//携带数据

    public EventType getEventType() {
        return mEventType;
    }

    public void setEventType(EventType eventType) {
        mEventType = eventType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
