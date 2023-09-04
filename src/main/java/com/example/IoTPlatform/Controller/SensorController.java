package com.example.IoTPlatform.Controller;

import com.example.IoTPlatform.model.Device;
import com.example.IoTPlatform.model.Sensor;
import com.example.IoTPlatform.repository.SensorRepository;
import com.example.IoTPlatform.service.impl.SensorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorController {


    @Autowired
    private SensorServiceImpl sensorService;


    @PostMapping("/addSensor")
    public Sensor addSensor(@RequestBody Sensor sensor){
        sensorService.addSensor(sensor);
        return sensor;
    }

    @GetMapping("/sensors")
    public List<Sensor> listSensors(){
        return sensorService.getAllSensors();
    }

    @PutMapping("/sensor/edit/{sensorId}")
    public Sensor updateSensor(@RequestBody Sensor sensor, @PathVariable String sensorId) {

        return sensorService.updateSensor(sensorId, sensor);
    }

    @DeleteMapping("/sensor/delete/{sensorId}")
    public String deleteSensor(@PathVariable String sensorId) {
        sensorService.deleteSensorById(sensorId);
        return sensorId;
    }

    @GetMapping("/{sensorName}")
    public List<Sensor> findSensorByName(@PathVariable String sensorName) {

        return sensorService.getSensorByName(sensorName);
    }
}
