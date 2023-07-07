package com.example.IoTPlatform.model;

import java.util.List;

public interface SensorIn {
    List<SensorV2> getAllSensorV2();

    SensorV2 getSensorV2ById(int sensorId);

    SensorV2 addNewSensorV2(SensorV2 sensorV2);


}
