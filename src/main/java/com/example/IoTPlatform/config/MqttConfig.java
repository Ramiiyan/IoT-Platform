package com.example.IoTPlatform.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MqttConfig{

    @Value("${mqtt.broker}")
    private String mqttBroker;

    @Value("${mqtt.clientId}")
    private String mqttClientId;

    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public IMqttClient mqttClient() throws Exception {
        IMqttClient mqttClient = new MqttClient(mqttBroker, mqttClientId, new MemoryPersistence());
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttClient.connect(mqttConnectOptions);
        return mqttClient;
    }
}

