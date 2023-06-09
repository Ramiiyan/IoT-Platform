package com.example.IoTPlatform;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttPublisher {

    private final IMqttClient mqttClient;


    @Autowired
    public MqttPublisher(IMqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void publish(String topic, String payload) throws Exception {
        MqttMessage message = new MqttMessage(payload.getBytes());
        mqttClient.publish(topic, message);
        System.out.println("Published message to topic: " + topic);
        System.out.println("Message: " + payload);
    }
}

