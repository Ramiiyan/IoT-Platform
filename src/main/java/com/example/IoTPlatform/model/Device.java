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
    private String mqttPubPath;
    private String mqttSubPath;
    private Map<String,String> deviceConfig = new HashMap<>();
    private Date creationDate;

    private ArrayList<Sensor> sensor;

    public Device(){}

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.mqttPubPath = mqttPubPath;
        this.mqttSubPath = mqttSubPath;
        this.creationDate = new Date();

    }

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath, Map<String, String> deviceConfig) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.mqttPubPath = mqttPubPath;
        this.mqttSubPath = mqttSubPath;
        this.deviceConfig = deviceConfig;
        this.creationDate = new Date();
    }

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath, Map<String, String> deviceConfig, ArrayList<Sensor> sensor) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.mqttPubPath = mqttPubPath;
        this.mqttSubPath = mqttSubPath;
        this.deviceConfig = deviceConfig;
        this.sensor = sensor;
        this.creationDate = new Date();
    }

    public Device(String deviceId, String deviceName, String deviceType, String mqttPubPath, String mqttSubPath, Map<String, String> deviceConfig, Date creationDate, ArrayList<Sensor> sensor) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.mqttPubPath = mqttPubPath;
        this.mqttSubPath = mqttSubPath;
        this.deviceConfig = deviceConfig;
        this.creationDate = creationDate;
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", mqttPubPath='" + mqttPubPath + '\'' +
                ", mqttSubPath='" + mqttSubPath + '\'' +
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
        return mqttPubPath;
    }

    public void setMqttPubPath(String mqttPubPath) {
        this.mqttPubPath = mqttPubPath;
    }

    public String getMqttSubPath() {
        return mqttSubPath;
    }

    public void setMqttSubPath(String mqttSubPath) {
        this.mqttSubPath = mqttSubPath;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Map<String, String> getDeviceConfig() {
        return deviceConfig;
    }

    public void setDeviceConfig(Map<String, String> deviceConfig) {
        this.deviceConfig = deviceConfig;
    }
}
