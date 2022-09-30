package com.okosiuta.rabitmqpoc.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.okosiuta.rabitmqpoc.constant.GenericConstants.RESOURCE_USAGE_QUEUE_NAME;

@Configuration
public class Config {

    @Bean
    public Queue statisticQueue() {
        return new Queue(RESOURCE_USAGE_QUEUE_NAME);
    }

    @Bean("consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(ConnectionFactory connectionFactory) {
        final var factory = new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setBatchListener(true);
        factory.setBatchSize(20);
        factory.setPrefetchCount(20);
        factory.setConsumerBatchEnabled(true);
        factory.setReceiveTimeout(30_000L); //30 SEC FOR DEMO PURPOSES - IN REAL PROJECT IT WOULD BE LIKE 1-2 SEC

        return factory;
    }
}
