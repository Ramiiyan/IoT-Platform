package com.example.IoTPlatform.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

//public record SensorData(Date ts, double sensor_value) {
//
//    public SensorData(double sensor_value){
//        this(new Date(),sensor_value);
//    }
//
//
//}

public class SensorData {
    @Id
    private String sensorDataId;
    private String sensorId;  // Reference to the sensor ID
    private Date timestamp;
    private Double value;

    public SensorData(String sensorId, Date timestamp, Double value) {
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.value = value;
    }

    // Getters and setters

    public String getSensorId() {
        return sensorId;
    }
}
