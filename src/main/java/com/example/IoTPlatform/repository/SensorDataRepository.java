package com.example.IoTPlatform.repository;

import com.example.IoTPlatform.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SensorDataRepository extends MongoRepository<SensorData,String> {

    @Query("{sensorId:'?0'}")
    List<SensorData> findItemBySensorId(String sensorId);

    long count();

}
