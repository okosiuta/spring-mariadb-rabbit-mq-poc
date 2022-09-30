package com.okosiuta.rabitmqpoc.queue.sender.impl;

import com.okosiuta.rabitmqpoc.data.Event;
import com.okosiuta.rabitmqpoc.queue.sender.EventSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventSenderImpl implements EventSender {

    private final RabbitTemplate template;

    @Override
    public <T extends Serializable> void sendEvent(String queue, Event<T> event) {
        template.convertAndSend(queue, event);

        log.info("Pushed event! Queue: {} - Event: {}", queue, event);
    }
}
