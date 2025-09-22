package com.ss.dtaskqueue.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String TASK_QUEUE = "task_queue";
    public static final String DLQ = "task_dlq";

    @Bean
    public Queue taskQueue() {
        return QueueBuilder.durable(TASK_QUEUE)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", DLQ)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DLQ).build();
    }
}
