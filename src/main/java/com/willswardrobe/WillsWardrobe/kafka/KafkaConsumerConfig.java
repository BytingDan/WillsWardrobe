package com.willswardrobe.WillsWardrobe.kafka;

import com.willswardrobe.WillsWardrobe.wardrobe.Wardrobe;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.handler.annotation.Header;

import java.util.HashMap;
import java.util.List;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private List<String> bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupID;

    @Bean
    public ConsumerFactory<String, Wardrobe> consumerFactory() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Wardrobe.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Wardrobe> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Wardrobe> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "wardrobeInfoTopic", groupId = "wardrobe-id")
    public void listenGroupId(Wardrobe wardrobe) {
        System.out.println("*-*-*-Received: " + wardrobe.getId() + "-*-*-*");
        System.out.println("*-*-*-Received: " + wardrobe.getItemName() + "-*-*-*");
        System.out.println("*-*-*-Received: " + wardrobe.getItemDescription() + "-*-*-*");
        System.out.println("*-*-*-Received: " + wardrobe.getItemPrice() + "-*-*-*");
        System.out.println("*-*-*-Received: " + wardrobe.getItemURL() + "-*-*-*");
        System.out.println("*-*-*-Received: " + wardrobe.getImageURL() + "-*-*-*");

//        System.out.println("Hit listener");
        //TODO create object to receive
        // Pass obj to db
        // pass obj to cache
        // Open API to receive cache based on ID and get the data
    }



}
