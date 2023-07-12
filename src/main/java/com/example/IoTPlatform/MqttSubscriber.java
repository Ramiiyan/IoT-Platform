package com.example.IoTPlatform;

import com.example.IoTPlatform.model.MqttResponse;
import com.example.IoTPlatform.model.SensorData;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber implements MqttCallback {

    private final IMqttClient mqttClient;
    private final int qos = 0;
    private static JSONObject jsonPayload;



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
        String payload = new String(message.getPayload());
        System.out.println("Message: " + payload);
        JSONObject jsonPayload = new JSONObject(payload);
        setJsonPayload(jsonPayload);

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

//    public double getSensorValue(String topic) throws MqttException{
//
//        System.out.println("============ getSensorValue ===============");
//        MqttWireMessage msg = mqttClient.subscribeWithResponse(topic).getResponse();
//
////        String payload = new String(msg.getPayload());
////        System.out.println("real-payload :" + );
////        JSONObject jsonObject = new JSONObject(payload);
////        double value = jsonObject.getDouble("value");
////        System.out.println("getSensorValue :" + value  );
////        return value;
//        return  getJsonPayload().getDouble("value");
//
//    }

//    public double getSensorValue(String topic) throws MqttException {
//        //SensorData sensorData;
//        //System.out.println("Getting Value Only....");
//        JSONObject jsonObject;
//        MqttMessage message = new MqttMessage();
//        mqttClient.subscribeWithResponse(topic, (myTopic,msg)->{
//            message.setPayload(msg.getPayload());
//            //System.out.println("Payload :" + new String(message.getPayload()));
//            //sensorData = new SensorData(1);
//        });
//        System.out.println("Payload :" + new String(message.getPayload()));
//        jsonObject = new JSONObject(new String(message.getPayload()));
//        return jsonObject.getDouble("value");
//
//    }


    public static JSONObject getJsonPayload() {
        return jsonPayload;
    }

    public static void setJsonPayload(JSONObject jsonPayload) {
        MqttSubscriber.jsonPayload = jsonPayload;
    }
}
