package com.example.IoTPlatform.model;

public class MqttResponse {
    private static double value =0.0;

    public static double getValue() {
        return value;
    }

    public static void setValue(double value) {
        MqttResponse.value = value;
    }
}
