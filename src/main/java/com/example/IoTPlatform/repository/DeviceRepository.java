package com.example.IoTPlatform.repository;

import com.example.IoTPlatform.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeviceRepository extends MongoRepository<Device, String> {

    @Query("{deviceName:'?0'}")
    List<Device> findItemByDeviceName(String deviceName);

    @Query(value = "{deviceType: '?0'}", fields="{'deviceId': 1,'deviceName':1, 'mqttPubPath':1,'mqttSubPath':1 }")
    List<Device> findAll(String deviceType);

    public long count();


}
