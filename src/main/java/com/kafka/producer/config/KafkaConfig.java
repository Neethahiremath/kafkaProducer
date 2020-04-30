package com.kafka.producer.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    private KafkaBaseConfig kafkaProps;

    @Autowired
    public KafkaConfig(KafkaBaseConfig kafkaBaseConfig) {
        this.kafkaProps = kafkaBaseConfig;
    }

    @Bean
    public KafkaProducer<String, String> producer() {
        return new KafkaProducer<>(getDefaultProperties());
    }

    private Properties getDefaultProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapServers());
        props.put(ProducerConfig.ACKS_CONFIG, kafkaProps.getAcks());
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaProps.getRetries());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProps.getBatchSize());
        props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProps.getLingerMs());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProps.getBufferMemory());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
                "io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor");
        return props;
    }
}
