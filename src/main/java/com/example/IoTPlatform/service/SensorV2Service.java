package com.example.IoTPlatform.service;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.model.SensorV2;

import java.util.List;

public interface SensorV2Service {
    public List<SensorV2> getSensorV2();

    public SensorV2 addSensorV2(SensorV2 sensorV2);

    public SensorV2 deleteSensorV2(int id );

    public SensorV2 updateSensorV2(int id , SensorV2 sensorV2);

    public SensorV2 updateSensorV2Data(int id, SensorData sensorData);
}
