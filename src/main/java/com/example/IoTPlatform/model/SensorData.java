package com.example.IoTPlatform.model;

import java.util.Date;

public record SensorData(Date ts, double sensor_value) {

    public SensorData(double sensor_value){
        this(new Date(),sensor_value);
    }


}
