package com.kafka.producer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("kafka")
@Getter
@Setter
public class KafkaBaseConfig {

    private String bootstrapServers;
    private String groupId;
    private String autoOffsetReset;
    private int sessionTimeoutMs;
    private Boolean enableAutoCommit;
    private int autoCommitIntervalMs;
    private int maxPollRecords;
    private int concurrency;
    private int pollTimeout;
    private String acks;
    private int batchSize;
    private long bufferMemory;
    private int lingerMs;
    private int retries;
    private String inputTopic;
}
