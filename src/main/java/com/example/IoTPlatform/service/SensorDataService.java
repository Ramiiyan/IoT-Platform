package com.example.IoTPlatform.service;

import com.example.IoTPlatform.model.SensorData;

import java.util.List;

public interface SensorDataService {

    public List<SensorData> findBySensorId(String sensorId);

    public SensorData saveSensorData(SensorData sensorData);

    public void deleteSensorData(String sensorDataId);

}
