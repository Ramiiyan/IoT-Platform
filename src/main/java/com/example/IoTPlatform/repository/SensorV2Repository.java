package com.example.IoTPlatform.repository;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.model.SensorV2;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorV2Repository extends MongoRepository<SensorV2,Integer> {
}
