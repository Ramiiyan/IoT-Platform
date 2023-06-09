package com.example.IoTPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IoTPlatformApplication {

	static MqttPublisher mqttPublisher;
	public static void main(String[] args) throws Exception{

		ApplicationContext context = SpringApplication.run(IoTPlatformApplication.class, args);

		mqttPublisher = context.getBean(MqttPublisher.class);
		mqttPublisher.publish("mytopic2", "Hello, MQTT!");

		MqttSubscriber mqttSubscriber = context.getBean(MqttSubscriber.class);
		mqttSubscriber.subscribe("mytopic");
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
