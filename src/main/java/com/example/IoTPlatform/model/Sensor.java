package com.example.IoTPlatform.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Sensor {

    @Id
    private String sensorId;
    private String sensorName;
    private String type;  // I/O type
    private double sensorValue;
    private Date creationDate;
    private int sensorPin;
    private Long timeStamp;

    public Sensor() {
    }

    public Sensor(String sensorId, String sensorName, String type){
        super();
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.type = type;
        this.creationDate = new Date();
    }

    public Sensor(String sensorId, String sensorName, String type, int sensorPin){
        super();
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.type = type;
        this.sensorPin = sensorPin;
        this.creationDate = new Date();
    }

    public Sensor(String sensorId, String sensorName, String type, int sensorPin, Long timeStamp){
        super();
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.type = type;
        this.sensorPin = sensorPin;
        this.timeStamp = timeStamp;
        this.creationDate = new Date();
    }

    public Sensor(String sensorId, String sensorName, String type, int sensorPin, double sensorValue){
        super();
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.type = type;
        this.sensorValue = sensorValue;
        this.sensorPin = sensorPin;
        this.creationDate = new Date();
    }

    public Sensor(String sensorId, String sensorName, String type, int sensorPin, double sensorValue, Date creationDate) {
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.type = type;
        this.sensorValue = sensorValue;
        this.sensorPin = sensorPin;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId=" + sensorId +
                ", sensorName='" + sensorName + '\'' +
                ", type='" + type + '\'' +
                ", sensorPin='" + sensorPin + '\'' +
                ", sensorValue=" + sensorValue + '\'' +
                ", creationDate=" + creationDate + '\'' +
                '}';
    }

    public double getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(double sensorValue) {
        this.sensorValue = sensorValue;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getSensorPin() {
        return sensorPin;
    }

    public void setSensorPin(int sensorPin) {
        this.sensorPin = sensorPin;
    }
}
