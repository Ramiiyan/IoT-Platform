package com.example.IoTPlatform.service;


import com.corundumstudio.socketio.SocketIOClient;
import com.example.IoTPlatform.model.Sensor;
import com.example.IoTPlatform.model.SocketMsgDto;
import com.example.IoTPlatform.model.SocketMessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SocketService {

    public void sendMessage(String room, String eventName, SocketIOClient senderClient, String message) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName,
                        new SocketMsgDto(SocketMessageType.SERVER, message));
            }
        }
    }

}

