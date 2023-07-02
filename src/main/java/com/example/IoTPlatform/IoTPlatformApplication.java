package com.example.IoTPlatform;

import com.example.IoTPlatform.model.Device;
import com.example.IoTPlatform.model.Sensor;
import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EnableMongoRepositories
public class IoTPlatformApplication implements CommandLineRunner {

	@Autowired
	DeviceRepository deviceItemRepo;

	List<Device> deviceList = new ArrayList<Device>();
	static MqttPublisher mqttPublisher;
	public static void main(String[] args) throws Exception {

		ApplicationContext context = SpringApplication.run(IoTPlatformApplication.class, args);
		//SpringApplication.run(IoTPlatformApplication.class, args);

		mqttPublisher = context.getBean(MqttPublisher.class);
		mqttPublisher.publish("mytopic2", "Hello, MQTT!");

		MqttSubscriber mqttSubscriber = context.getBean(MqttSubscriber.class);
		mqttSubscriber.subscribe("mytopic");

	}

	public void run(String... args){
		createDeviceSensor();
		showAllDevices();
	}

	public void createDeviceSensor(){

		System.out.println("Device and Sensors creation initiated");
		Device d1 = new Device("3","testD3","Esp32","/D3_pub/","/D3_sub/");
		ArrayList<Sensor> mySensors = new ArrayList<Sensor>();
		mySensors.add(new Sensor(1124,"sm3","input", new SensorData(44.85)));
		mySensors.add(new Sensor(1115,"sm4","input", new SensorData(46.14)));
		mySensors.add(new Sensor(1116,"sm5","input", new SensorData(55.5)));
		d1.setSensor(mySensors);

		deviceItemRepo.save(d1);
		System.out.println("Device and Sensors creation completed.");

//		System.out.println("Sample data insertion..");
//		mySensors.get(0).setSensor_data(new SensorData(44.85));
//		mySensors.get(0).setSensor_data(new SensorData(45.65));
//		mySensors.get(0).setSensor_data(new SensorData(46.52));
//		mySensors.get(0).setSensor_data(new SensorData(47.53));

	}

	public void showAllDevices(){

		deviceList = deviceItemRepo.findAll();
		deviceList.forEach(device -> System.out.println(device.toString()));

	}





	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
		return String.format("Hello %s!", name);
	}

	@GetMapping("/publish")
	public String publisher(@RequestParam(value = "payload", defaultValue = "{\"test\":\"passed\"}") String payload){

		try {
			mqttPublisher.publish("mytopic2", payload);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return String.format("Published Payload: %s!", payload);
	}

}
