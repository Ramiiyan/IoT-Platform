package com.example.IoTPlatform.service;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.model.Sensor;

import java.util.List;

public interface SensorService {
    public List<Sensor> getSensorV2();

    public Sensor addSensorV2(Sensor sensorV2);

    public Sensor deleteSensorV2(int id );

    public Sensor updateSensorV2(int id , Sensor sensorV2);

    public Sensor updateSensorV2Data(int id, SensorData sensorData);
}
