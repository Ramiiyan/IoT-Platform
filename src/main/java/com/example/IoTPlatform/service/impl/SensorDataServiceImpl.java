package com.example.IoTPlatform.service.impl;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.repository.SensorDataRepository;
import com.example.IoTPlatform.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataServiceImpl implements SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Override
    public List<SensorData> findBySensorId(String sensorId) {
        return sensorDataRepository.findItemBySensorId(sensorId);
    }

    @Override
    public SensorData saveSensorData(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    @Override
    public void deleteSensorData(String sensorDataId) {
        sensorDataRepository.deleteById(sensorDataId);
    }
}
