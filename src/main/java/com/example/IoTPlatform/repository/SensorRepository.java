package com.example.IoTPlatform.repository;

import com.example.IoTPlatform.model.Device;
import com.example.IoTPlatform.model.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor,String> {

    @Query("{deviceName:'?0'}")
    List<Sensor> findItemBySensorName(String sensorName);


    public long count();

}
