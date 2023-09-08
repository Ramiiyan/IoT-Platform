package com.example.IoTPlatform.service;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MqttMessagingService {

    private final String MQTT_SERVICE = "MqttMessagingService";

    @Autowired
    private IMqttClient mqttClient;

    public void publish(final String topic, final String payload, int qos, boolean retained)
            throws MqttPersistenceException, MqttException, IOException, InterruptedException {

        /* Wrapping the payload in the MqttMessage object */
        MqttMessage mqttMessage = new MqttMessage(payload.getBytes());
        //mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        mqttClient.publish(topic, mqttMessage);
        System.out.printf("[%s]Published message to topic: %s \n",MQTT_SERVICE, topic);
        System.out.printf("[%s]Message: %s \n",MQTT_SERVICE, payload);
    }
    public void subscribe(final String topic) throws MqttException, InterruptedException {
        mqttClient.subscribeWithResponse(topic, (tpic, msg) -> {
            /* Extracing the payload */
            byte[] payload = msg.getPayload();
            String message = new String(payload);
            System.out.printf("[%s] Received Message: %s \n", MQTT_SERVICE, message);


        });
    }

}
