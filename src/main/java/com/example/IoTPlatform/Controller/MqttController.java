package com.example.IoTPlatform.Controller;

import com.example.IoTPlatform.Socket.SocketClient;
import com.example.IoTPlatform.service.MqttMessagingService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MqttController {

    @Autowired
    private MqttMessagingService mqttMessagingService;

    @Autowired
    private ConfigurableApplicationContext context;

    SocketClient socketClient = new SocketClient();

    @RequestMapping("/Mqtt")
    public String mqttcall() throws MqttException, InterruptedException, IOException {

        final String subTopic = "subTopic";
        final String pubTopic = "pubTopic";

        mqttMessagingService.subscribe(subTopic);
//        socketClient.socketInit();
//        socketClient.publishSocketData();
        mqttMessagingService.publish(pubTopic, "{\"test\":\"HelloMQTT\"}", 0, false);


        //context.close();

        return "OK\n";
    }

}
