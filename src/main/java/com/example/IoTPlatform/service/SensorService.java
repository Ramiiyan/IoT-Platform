package com.example.IoTPlatform.service;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.model.Sensor;

import java.util.List;

public interface SensorService {
    public List<Sensor> getAllSensors();

    public Sensor getSensorById(String id);

    public Sensor addSensor(Sensor sensor);

    public Sensor deleteSensorById(String id );

    public Sensor updateSensor(String id , Sensor sensor);

    public Sensor updateSensorData(String id, SensorData sensorData);
}
