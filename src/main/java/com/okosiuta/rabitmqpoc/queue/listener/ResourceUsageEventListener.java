package com.okosiuta.rabitmqpoc.queue.listener;

import com.okosiuta.rabitmqpoc.data.Action;
import com.okosiuta.rabitmqpoc.data.ResourceUsageEvent;
import com.okosiuta.rabitmqpoc.data.model.Certificate;
import com.okosiuta.rabitmqpoc.data.model.ResourceUsage;
import com.okosiuta.rabitmqpoc.service.ResourceUsageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.okosiuta.rabitmqpoc.constant.GenericConstants.RESOURCE_USAGE_QUEUE_NAME;
import static java.util.stream.Collectors.summingLong;

@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = RESOURCE_USAGE_QUEUE_NAME, containerFactory = "consumerBatchContainerFactory")
public class ResourceUsageEventListener {

    private final ResourceUsageService resourceUsageService;

    @RabbitHandler
    public void handle(List<ResourceUsageEvent<Certificate>> events) {
        log.info("Event processing has started! Queue: {} - Events: {}", RESOURCE_USAGE_QUEUE_NAME, events);

        var newUsageCalculationMap = events.stream()
            .collect(Collectors.groupingBy(ResourceUsageEvent::getResourceType,
                summingLong(e -> e.getAction() == Action.CREATE ? 1 : -1)));
        var usage = resourceUsageService.findAll();
        var usageMap = usage.stream()
            .collect(Collectors.groupingBy(ResourceUsage::getResourceType));

        newUsageCalculationMap.forEach((k, v) -> {
            Optional.ofNullable(usageMap.get(k))
                .map(e -> e.get(0))
                .ifPresentOrElse(ru -> ru.setCount(v + ru.getCount()),
                    () -> {
                        var newUsage = new ResourceUsage();

                        newUsage.setResourceType(k);
                        newUsage.setCount(v);

                        usage.add(newUsage);
                    });
        });

        resourceUsageService.save(usage);
    }
}
