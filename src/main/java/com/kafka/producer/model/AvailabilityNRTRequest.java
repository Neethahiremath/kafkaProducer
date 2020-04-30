package com.kafka.producer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityNRTRequest {

    private String modelNumber;
    private String vendorNumber;
    private String itemId;
    private Integer itemType;
    private String node;
    private Integer quantity;
    private Boolean sourcingEnabled;
    private String sellUnit;
    private String shipUnit;
}
