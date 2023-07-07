package com.example.IoTPlatform.model;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class Sensor {

    @Id
    private int sensor_id;
    private String sensor_name;
    private String type;  // I/O type
    private SensorData sensor_data;
    private Date creationDate;

    public Sensor() {
    }

    public Sensor(int sensor_id, String sensor_name, String type){
        super();
        this.sensor_id = sensor_id;
        this.sensor_name = sensor_name;
        this.type = type;
        this.creationDate = new Date();
    }

    public Sensor(int sensor_id, String sensor_name, String type, SensorData sensor_data){
        super();
        this.sensor_id = sensor_id;
        this.sensor_name = sensor_name;
        this.type = type;
        this.sensor_data = sensor_data;
        this.creationDate = new Date();
    }

    public Sensor(int sensor_id, String sensor_name, String type, SensorData sensor_data, Date creationDate) {
        this.sensor_id = sensor_id;
        this.sensor_name = sensor_name;
        this.type = type;
        this.sensor_data = sensor_data;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensor_id=" + sensor_id +
                ", sensor_name='" + sensor_name + '\'' +
                ", type='" + type + '\'' +
                ", sensor_data=" + sensor_data +
                ", creationDate=" + creationDate +
                '}';
    }

    public SensorData getSensor_data() {
        return sensor_data;
    }

    public void setSensor_data(SensorData sensor_data) {
        this.sensor_data = sensor_data;
    }

    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
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

}
