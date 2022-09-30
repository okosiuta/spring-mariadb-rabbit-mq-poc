package com.okosiuta.rabitmqpoc.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Getter
@ToString
@RequiredArgsConstructor
public class Event<T extends Serializable> implements Serializable {

    private final Action action;

    @Nullable
    private final T body;
}
