package com.example.IoTPlatform;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig{

    @Value("${mqtt.broker}")
    private String mqttBroker;

    @Value("${mqtt.clientId}")
    private String mqttClientId;

    @Bean
    public IMqttClient mqttClient() throws Exception {
        IMqttClient mqttClient = new MqttClient(mqttBroker, mqttClientId, new MemoryPersistence());
        mqttClient.connect();
        return mqttClient;
    }
}

