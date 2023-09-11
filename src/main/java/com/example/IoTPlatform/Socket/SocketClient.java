package com.example.IoTPlatform.Socket;

import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;

@Slf4j
public class SocketClient {

    static private Socket socket;
    public Socket socketInit() {
        try {
            socket = IO.socket("http://localhost:" + 9092 + "/?room=a");
            socket.connect();
            log.info("socket.connect()");
            return socket;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void publishSocketData(Object... payload){
        socket.emit("send_message", payload);
    }
}
