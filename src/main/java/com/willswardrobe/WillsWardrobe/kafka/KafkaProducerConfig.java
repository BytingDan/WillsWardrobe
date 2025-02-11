package com.willswardrobe.WillsWardrobe.kafka;

import com.willswardrobe.WillsWardrobe.wardrobe.Wardrobe;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private List<String> bootstrapServers;

    @Bean("wardrobeProducerFactory")
    public ProducerFactory<String, Wardrobe> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, Wardrobe>(props);
    }

    @Bean("wardrobeKafkaTemplate")
    public KafkaTemplate<String, Wardrobe> wardrobeKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
