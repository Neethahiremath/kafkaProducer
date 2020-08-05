package com.kafka.producer.service;

import com.google.gson.Gson;
import com.kafka.producer.config.KafkaBaseConfig;
import com.kafka.producer.model.AvailabilityNRTRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProduceService {

    @Autowired
    private KafkaProducer<String, String> producer;

    @Autowired
    private KafkaBaseConfig kafkaBaseConfig;

    @Autowired
    private Gson gson;

    public void publishMessage(AvailabilityNRTRequest availabilityNRTRequest) {
        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaBaseConfig.getInputTopic(), availabilityNRTRequest.getName(),
                gson.toJson(availabilityNRTRequest));

        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                log.error("Error occurred while publishing kafka message. Message: {}", availabilityNRTRequest,
                        exception);
            } else {
                log.info("Message sent to Topic: {}, Partition: {}, Offset: {}, Message: {}",
                        metadata.topic(), metadata.partition(), metadata.offset(), availabilityNRTRequest);
            }
        });
    }

}
