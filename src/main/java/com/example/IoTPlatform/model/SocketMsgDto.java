package com.example.IoTPlatform.model;

import lombok.Data;

@Data
public class SocketMsgDto {
    private SocketMessageType type;
    private String message;
    private String room;

    public SocketMsgDto() {
    }

    public SocketMsgDto(SocketMessageType type, String message) {
        this.type = type;
        this.message = message;
    }
}