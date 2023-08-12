package com.example.IoTPlatform.Controller;

import com.example.IoTPlatform.model.Device;
import com.example.IoTPlatform.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/hello2")
    public String hello2(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("Hello:2 %s!", name);
    }

    @PostMapping("/addDevice")
    public Device addDevice(@RequestBody Device device){
        deviceRepository.save(device);

        return device;
    }

    @GetMapping
    public List<Device> listDevices(){
        return deviceRepository.findAll();
    }

    @PutMapping("/{deviceId}")
    public Device updateDevice(@RequestBody Device device, @PathVariable String deviceId) {
        device.setDeviceId(deviceId);
        deviceRepository.save(device);
        return device;
    }

    @DeleteMapping("/{deviceId}")
    public String deleteDevice(@PathVariable String deviceId) {
        deviceRepository.deleteById(deviceId);
        return deviceId;
    }

    @GetMapping("/{deviceName}")
    public List<Device> findDeviceByName(@PathVariable String deviceName) {
        return deviceRepository.findItemByDeviceName(deviceName);
    }

//    @GetMapping("/publish")
//    public String publisher(@RequestParam(value = "payload", defaultValue = "{\"test\":\"passed\"}") String payload){
//
//        try {
//            mqttPublisher.publish("mytopic2", payload);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return String.format("Published Payload: %s!", payload);
//    }

//    @GetMapping("/subscribe/{topic}")
//    public String subscriber(@PathVariable String topic) {
//        double value;
//        try {
//            System.out.println("get-subscribe topic:" + topic);
//
//            value = MqttSubscriber.getJsonPayload().getDouble("value");
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return String.format("Subscribed Topic: %s \n Payload: %f", topic, value);
//    }

}
