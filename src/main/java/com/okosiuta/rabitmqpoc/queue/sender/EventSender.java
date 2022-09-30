package com.okosiuta.rabitmqpoc.queue.sender;

import com.okosiuta.rabitmqpoc.data.Event;

import java.io.Serializable;

public interface EventSender {

    <T extends Serializable> void sendEvent(String queue, Event<T> event);
}
