package com.example.IoTPlatform;

import com.example.IoTPlatform.model.SensorData;
import com.example.IoTPlatform.model.Sensor;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber implements MqttCallback {

    private final IMqttClient mqttClient;
    private final int qos = 0;
    private static JSONObject jsonPayload;



    Sensor s23 = new Sensor("663","MyTestSensorV2_663","input");

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
        //SensorV2 s23 = new SensorV2(663,"MyTestSensorV2_663","input");
        //int sensorId = s23.getsensorId();
//        Criteria criteria = Criteria.where("sensorId").is(s23.getsensorId());
//        Query query = new Query(criteria);

        //sensorV2Service.addSensorV2(s23);
        //SensorV2 s24 = new SensorV2(664,"664_test_sensor","Input",50.98);
        //sensorV2Service.addSensorV2(s24);
        //sensorV2Service.updateSensorV2(664,s24);
        double sensorValue =  jsonPayload.getDouble("value");


//        Update update = new Update().push("samples", new SensorData(33.9))
//                .min("first", s23.getTimeStamp())
//                .max("last", s23.getTimeStamp());
//
//        mongoTemplate.upsert(query,update, SensorV2.class);

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
