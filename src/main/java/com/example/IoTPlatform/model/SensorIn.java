package com.example.IoTPlatform.model;

import java.util.List;

public interface SensorIn {
    List<Sensor> getAllSensorV2();

    Sensor getSensorV2ById(int sensorId);

    Sensor addNewSensorV2(Sensor sensorV2);


}
