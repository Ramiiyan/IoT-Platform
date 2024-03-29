package com.example.IoTPlatform.service;

import com.example.IoTPlatform.Socket.SocketClient;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MqttMessagingService {

    private final String MQTT_SERVICE = "MqttMessagingService";

    @Autowired
    private IMqttClient mqttClient;

    private int i = 0;
    private SocketClient socketClient  = new SocketClient(); //WebSocket Client Initialization & Declaration.

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
        i++;
        System.out.printf("MQTT subscribing... [%d]", i);
        socketClient.socketInit();
        if (!mqttClient.isConnected()) {
            // You may want to handle reconnection here or throw an exception
            System.out.printf("MQTT client is not connected. Reconnecting...[%d]", i);
            mqttClient.connect();
        }
//        mqttClient.subscribeWithResponse(topic, (tpic, msg) -> {
//            /* Extracing the payload */
//            byte[] payload = msg.getPayload();
//            String message = new String(payload);
//            System.out.printf("[%s] Received Message: %s \n", MQTT_SERVICE, message);
//
//            /* This is the Websocket Server Message data format */
//            JSONObject testJsonObj = new JSONObject();
//            testJsonObj.put("type", "CLIENT");
//            testJsonObj.put("room", "a");
//            testJsonObj.put("message", message);
//
//            /* WebSocket Implementation */
//            socketClient.publishSocketData(testJsonObj);
//
//
//        });
        mqttClient.subscribe(topic);
    }

}
