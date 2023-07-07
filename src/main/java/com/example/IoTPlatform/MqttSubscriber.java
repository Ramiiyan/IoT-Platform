package com.example.IoTPlatform;

import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;
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
//    public double subscribeWithResponse(){
//
//    }
    public double getSensorValue(String topic) throws MqttException {

        //System.out.println("Getting Value Only....");
        JSONObject jsonObject;
        MqttMessage message = new MqttMessage();
        mqttClient.subscribeWithResponse(topic, (myTopic,msg)->{
            message.setPayload(msg.getPayload());
            //System.out.println("Payload :" + new String(message.getPayload()));
        });
        System.out.println("Payload :" + new String(message.getPayload()));
        //jsonObject = new JSONObject(new String(message.getPayload()));
        //return jsonObject.getDouble("value");
        return 0.0;
    }
}
