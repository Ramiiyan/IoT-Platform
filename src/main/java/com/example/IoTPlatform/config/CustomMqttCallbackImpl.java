package com.example.IoTPlatform.config;

import com.example.IoTPlatform.Socket.SocketClient;
import com.example.IoTPlatform.model.Sensor;
import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.service.impl.SensorDataServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Log4j2
@Component
public class CustomMqttCallbackImpl implements MqttCallback {

    @Autowired
    private SensorDataServiceImpl sensorDataService;

    private SocketClient socketClient  = new SocketClient(); //WebSocket Client Initialization & Declaration.
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("Mqtt Client disconnected: " + throwable.getMessage());

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("Mqtt Client messageArrived called");
//        log.info("s: "+ s);
//        log.info("mqttMessage: "+ mqttMessage.toString());

        /* Extracing the payload */
        byte[] payload = mqttMessage.getPayload();
        String message = new String(payload);
        log.info("Received MQTT Message: " + message);

        /* This is the Websocket Server Message data format */
        JSONObject testJsonObj = new JSONObject();
        testJsonObj.put("type", "CLIENT");
        testJsonObj.put("room", "a");
        testJsonObj.put("message", message);

        /* WebSocket Implementation */
        socketClient.publishSocketData(testJsonObj);
        log.info("Data published to WebSocket");

        /* SensorData DB update */
        //TODO
        // update sensorData database
        ObjectMapper objectMapper = new ObjectMapper();
        Date currentDateTime = new Date("YYYY-mm-ddTHH:MM:ss");
        List<Sensor> sensors = objectMapper.readValue(message, new TypeReference<List<Sensor>>(){});
        for (Sensor sensor: sensors) {
            sensorDataService.saveSensorData(new SensorData(sensor.getSensorId(), currentDateTime, sensor.getsensorValue()));
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("deliveryComplete.");
    }
}
