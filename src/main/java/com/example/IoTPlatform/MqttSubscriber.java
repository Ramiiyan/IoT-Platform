package com.example.IoTPlatform;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber implements MqttCallback {

    private final IMqttClient mqttClient;
    private final int qos = 0;

    @Autowired
    public MqttSubscriber(IMqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Received message on topic: " + topic);
        System.out.println("Qos: " + message.getQos());
        System.out.println("Message: " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Not used in this example
    }

    public void subscribe(String topic) throws Exception {
        mqttClient.setCallback(this);
        mqttClient.subscribe(topic, qos);
        System.out.println("Subscribed to topic: " + topic);
    }
}
