package com.example.IoTPlatform;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MqttHandler {

    private MqttAsyncClient mqttClient;

    public void MqttSensorHandler() throws MqttException {
        // Create the MQTT client
        String broker = "tcp://mqtt.example.com:1883";  // Replace with your broker URL
        String clientId = "myClientId";  // Replace with your client ID
        mqttClient = new MqttAsyncClient(broker, clientId, new MemoryPersistence());
        mqttClient.connect().waitForCompletion();  // Connect to the broker
        mqttClient.subscribe("tasda",0);
    }

    public double getSensorValue(String topic) throws MqttException {
        mqttClient.subscribe("tasda",0);
        MqttWireMessage message = mqttClient.subscribe(topic,0).getResponse();
        String payload = new String(message.getPayload());
        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.getDouble("value");
    }
}
