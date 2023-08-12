package com.example.IoTPlatform;

import com.example.IoTPlatform.model.*;
import com.example.IoTPlatform.repository.DeviceRepository;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EnableMongoRepositories
public class IoTPlatformApplication{

	@Autowired
	DeviceRepository deviceItemRepo;

	List<Device> deviceList = new ArrayList<Device>();

	SensorV2 s21 = new SensorV2(662,"MyTestSensorV2_662","input");
	static MqttPublisher mqttPublisher;
	static MqttSubscriber mqttSubscriber;

	@Autowired
	MongoTemplate mongoTemplate;
	public static void main(String[] args) throws Exception {

		//ApplicationContext context = SpringApplication.run(IoTPlatformApplication.class, args);
		SpringApplication.run(IoTPlatformApplication.class, args);

//		mqttPublisher = context.getBean(MqttPublisher.class);
//		mqttPublisher.publish("mytopic2", "{\"test\":\"HelloMQTT\"}");

//		mqttSubscriber = context.getBean(MqttSubscriber.class);
//		mqttSubscriber.subscribe("myTopic");
//		double val= MqttResponse.getValue();
//		System.out.println("getValue :" + val);
		//mqttSubscriber.subscribe("mytopic");
	}

	public Query testSensorV2(){
		System.out.println("Sensor V2.... Testing..........");
		//Device d2 = new Device("66","testD66","Esp32","/D66_pub/","/D66_sub/");
		s21.setTimeStamp(1573833152L);
		s21.setsensorValue(24.2);
		System.out.println("Criteria creation....");
		Criteria criteria = Criteria.where("sensorId").is(s21.getsensorId())
				.and("bucketSize").lt(1000)
				.and("date").is(s21.getCreationDate());

		return Query.query(criteria);

	}

	public void updateData(Query query){

		int sensorId = s21.getsensorId();
		Update update = new Update().push("samples", new SensorData(33.9))
				.inc("bucketSize",sensorId)
				.min("first", s21.getTimeStamp())
				.max("last", s21.getTimeStamp());

		mongoTemplate.upsert(query,update, SensorV2.class);
	}

	public void createDeviceSensor(){

		System.out.println("Device and Sensors creation initiated");
		Device d1 = new Device("3","testD3","Esp32","/D3_pub/","/D3_sub/");
		ArrayList<Sensor> mySensors = new ArrayList<Sensor>();
		mySensors.add(new Sensor(1124,"sm3","input", new SensorData(44.85)));
		mySensors.add(new Sensor(1115,"sm4","input", new SensorData(46.14)));
		mySensors.add(new Sensor(1117,"sm7","input", new SensorData(33.5)));
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





//	@GetMapping("/hello")
//	public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
//		return String.format("Hello %s!", name);
//	}
//
//	@GetMapping("/publish")
//	public String publisher(@RequestParam(value = "payload", defaultValue = "{\"test\":\"passed\"}") String payload){
//
//		try {
//			mqttPublisher.publish("mytopic2", payload);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return String.format("Published Payload: %s!", payload);
//	}
//
//	@GetMapping("/subscribe/{topic}")
//	public String subscriber(@PathVariable String topic) {
//		double value;
//		try {
//			System.out.println("get-subscribe topic:" + topic);
//
//			value = MqttSubscriber.getJsonPayload().getDouble("value");
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return String.format("Subscribed Topic: %s \n Payload: %f", topic, value);
//	}

}
