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
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor getSensorById(String id) {
        return sensorRepository.findById(id).orElseThrow();
    }

    public List<Sensor> getSensorByName(String sensorName){
        return  sensorRepository.findItemBySensorName(sensorName);
    }

    @Override
    public Sensor addSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor deleteSensorById(String id) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow();
        sensorRepository.delete(sensor);
        return sensor;
    }

    @Override
    public Sensor updateSensor(String id, Sensor sensor) {

        Sensor sensorVar = sensorRepository.findById(id).orElseThrow();
        sensorVar.setCreationDate(sensor.getCreationDate());
        sensorVar.setTimeStamp(sensor.getTimeStamp());
        sensorVar.setsensorValue(sensor.getsensorValue());
        sensorVar.setType(sensor.getType());
        sensorVar.setsensorName(sensor.getsensorName());
        sensorVar.setSensorPin(sensor.getSensorPin());
        sensorVar.setSensor_data(new SensorData(0.00));
        sensorRepository.save(sensorVar);

        return sensorVar;
    }

    @Override
    public Sensor updateSensorData(String id, SensorData newSensorData) {
        Sensor sensorData = sensorRepository.findById(id).orElseThrow();
        sensorData.setSensor_data(newSensorData);
        sensorRepository.save(sensorData);

        return sensorData;
    }

}
