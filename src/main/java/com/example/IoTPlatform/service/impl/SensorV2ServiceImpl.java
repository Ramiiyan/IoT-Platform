package com.example.IoTPlatform.service.impl;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.model.SensorV2;
import com.example.IoTPlatform.repository.SensorV2Repository;
import com.example.IoTPlatform.service.SensorV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorV2ServiceImpl implements SensorV2Service {

    @Autowired
    private SensorV2Repository sensorV2Repository;

    @Override
    public List<SensorV2> getSensorV2() {
        return sensorV2Repository.findAll();
    }

    @Override
    public SensorV2 addSensorV2(SensorV2 sensorV2) {
        return sensorV2Repository.save(sensorV2);
    }

    @Override
    public SensorV2 deleteSensorV2(int id) {
        SensorV2 sensorV2 = sensorV2Repository.findById(id).get();
        sensorV2Repository.delete(sensorV2);
        return sensorV2;
    }

    @Override
    public SensorV2 updateSensorV2(int id, SensorV2 sensorV2) {

        SensorV2 sensorV2Var = sensorV2Repository.findById(id).get();
        sensorV2Var.setCreationDate(sensorV2.getCreationDate());
        sensorV2Var.setTimeStamp(sensorV2.getTimeStamp());
        sensorV2Var.setsensorValue(sensorV2.getsensorValue());
        sensorV2Var.setType(sensorV2.getType());
        sensorV2Var.setsensorName(sensorV2.getsensorName());
        sensorV2Var.setSensor_data(new SensorData(0.00));
        sensorV2Repository.save(sensorV2Var);

        return sensorV2Var;
    }

    @Override
    public SensorV2 updateSensorV2Data(int id, SensorData sensorData) {
        SensorV2 sensorV2Data = sensorV2Repository.findById(id).get();
        sensorV2Data.setSensor_data(sensorData);
        sensorV2Repository.save(sensorV2Data);

        return sensorV2Data;
    }

}
