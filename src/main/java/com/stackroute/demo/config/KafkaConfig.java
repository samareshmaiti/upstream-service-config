package com.stackroute.demo.config;

import com.stackroute.demo.domain.Activities;
import com.stackroute.demo.domain.Objects;
import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;


@Configuration
@PropertySource(value = "classpath:application.properties")
public class KafkaConfig{
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<String,Object>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, Activities> producerFactory() {
        return new DefaultKafkaProducerFactory<String,Activities>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Activities> kafkaTemplate() {

        return new KafkaTemplate<String,Activities>(producerFactory());
    }
}
