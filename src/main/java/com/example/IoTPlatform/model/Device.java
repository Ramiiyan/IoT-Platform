package com.example.IoTPlatform.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Document
public class Device {

    @Id
    private String deviceId;
    private String deviceName;
    private String deviceType;
    private String MqttPubPath;
    private String MqttSubPath;
    private Map<String,String> deviceSettings = new HashMap<>();
    private Date creationDate;

    private ArrayList<Sensor> sensor;

    public Device(){}

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.MqttPubPath = mqttPubPath;
        this.MqttSubPath = mqttSubPath;
        this.creationDate = new Date();

    }

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath, Map<String, String> deviceSettings) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.MqttPubPath = mqttPubPath;
        this.MqttSubPath = mqttSubPath;
        this.deviceSettings = deviceSettings;
        this.creationDate = new Date();
    }

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath, Map<String, String> deviceSettings, ArrayList<Sensor> sensor) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.MqttPubPath = mqttPubPath;
        this.MqttSubPath = mqttSubPath;
        this.deviceSettings = deviceSettings;
        this.sensor = sensor;
        this.creationDate = new Date();
    }

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath, Map<String, String> deviceSettings, Date creationDate, ArrayList<Sensor> sensor) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.MqttPubPath = mqttPubPath;
        this.MqttSubPath = mqttSubPath;
        this.deviceSettings = deviceSettings;
        this.creationDate = creationDate;
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", MqttPubPath='" + MqttPubPath + '\'' +
                ", MqttSubPath='" + MqttSubPath + '\'' +
                ", creationDate=" + creationDate +
                ", sensor=" + sensor +
                '}';
    }

    public ArrayList<Sensor> getSensor() {
        return sensor;
    }

    public void setSensor(ArrayList<Sensor> sensor) {
        this.sensor = sensor;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMqttPubPath() {
        return MqttPubPath;
    }

    public void setMqttPubPath(String mqttPubPath) {
        this.MqttPubPath = mqttPubPath;
    }

    public String getMqttSubPath() {
        return MqttSubPath;
    }

    public void setMqttSubPath(String mqttSubPath) {
        this.MqttSubPath = mqttSubPath;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Map<String, String> getDeviceSettings() {
        return deviceSettings;
    }

    public void setDeviceSettings(Map<String, String> deviceSettings) {
        this.deviceSettings = deviceSettings;
    }
}
