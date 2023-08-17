package com.example.IoTPlatform.service.impl;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.model.Sensor;
import com.example.IoTPlatform.repository.SensorRepository;
import com.example.IoTPlatform.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public List<Sensor> getSensorV2() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor addSensorV2(Sensor sensorV2) {
        return sensorRepository.save(sensorV2);
    }

    @Override
    public Sensor deleteSensorV2(int id) {
        Sensor sensorV2 = sensorRepository.findById(id).get();
        sensorRepository.delete(sensorV2);
        return sensorV2;
    }

    @Override
    public Sensor updateSensorV2(int id, Sensor sensorV2) {

        Sensor sensorV2Var = sensorRepository.findById(id).get();
        sensorV2Var.setCreationDate(sensorV2.getCreationDate());
        sensorV2Var.setTimeStamp(sensorV2.getTimeStamp());
        sensorV2Var.setsensorValue(sensorV2.getsensorValue());
        sensorV2Var.setType(sensorV2.getType());
        sensorV2Var.setsensorName(sensorV2.getsensorName());
        sensorV2Var.setSensor_data(new SensorData(0.00));
        sensorRepository.save(sensorV2Var);

        return sensorV2Var;
    }

    @Override
    public Sensor updateSensorV2Data(int id, SensorData sensorData) {
        Sensor sensorV2Data = sensorRepository.findById(id).get();
        sensorV2Data.setSensor_data(sensorData);
        sensorRepository.save(sensorV2Data);

        return sensorV2Data;
    }

}
