package com.kafka.producer.controller;

import com.google.gson.Gson;
import com.kafka.producer.model.AvailabilityNRTRequest;
import com.kafka.producer.service.KafkaProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaProduceService kafkaProduceService;
    @Autowired
    private Gson gson;

    @PostMapping(value = "/sendMessage")
    public ResponseEntity<String> sendMessageToKafkaTopic(@RequestBody AvailabilityNRTRequest availabilityNRTRequest) {

        kafkaProduceService.publishMessage(availabilityNRTRequest);
        return new ResponseEntity<>(gson.toJson(availabilityNRTRequest), HttpStatus.OK);

    }
}
