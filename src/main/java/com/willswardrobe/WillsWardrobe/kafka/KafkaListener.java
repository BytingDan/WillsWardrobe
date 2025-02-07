package com.willswardrobe.WillsWardrobe.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaHead {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1").partitions(10).replicas(1).build();
    }

    @KafkaListener(id="myId", topics="topic1")
    public void listen(String in) {
        System.out.println(in);
    }
}
