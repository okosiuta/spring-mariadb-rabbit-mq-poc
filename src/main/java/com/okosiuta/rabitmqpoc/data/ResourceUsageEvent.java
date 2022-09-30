package com.okosiuta.rabitmqpoc.data;

import com.okosiuta.rabitmqpoc.data.model.ResourceType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public final class ResourceUsageEvent<T extends Serializable> extends Event<T> {

    private final ResourceType resourceType;

    public ResourceUsageEvent(Action action, T body, ResourceType resourceType) {
        super(action, body);
        this.resourceType = resourceType;
    }
}
