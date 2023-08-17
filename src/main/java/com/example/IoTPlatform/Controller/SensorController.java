package com.example.IoTPlatform.Controller;

import com.example.IoTPlatform.model.Device;
import com.example.IoTPlatform.model.Sensor;
import com.example.IoTPlatform.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;


    @PostMapping("/addSensor")
    public Sensor addSensor(@RequestBody Sensor sensor){
        sensorRepository.save(sensor);

        return sensor;
    }

    @GetMapping
    public List<Sensor> listSensors(){
        return sensorRepository.findAll();
    }

    @PutMapping("/{sensorId}")
    public Sensor updateSensor(@RequestBody Sensor sensor, @PathVariable String sensorId) {
        sensor.setSensorId(sensorId);
        sensorRepository.save(sensor);
        return sensor;
    }

    @DeleteMapping("/{sensorId}")
    public int deleteSensor(@PathVariable int sensorId) {
        sensorRepository.deleteById(sensorId);
        return sensorId;
    }

    @GetMapping("/{sensorName}")
    public List<Sensor> findSensorByName(@PathVariable String sensorName) {
        return sensorRepository.findItemBySensorName(sensorName);
    }
}
