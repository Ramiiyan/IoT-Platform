package com.example.IoTPlatform.Socket;

import com.example.IoTPlatform.model.Sensor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public class SocketClient {

    ObjectMapper objectMapper = new ObjectMapper();
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
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

    public void publishSocketDataToMongoDB(String msg){

        List<Sensor> sensors = null;
        Date currentDateTime;

        try {
            log.info("SensorData publisher to MongoDB");

            currentDateTime = formatter.parse("<YYYY-mm-ddTHH:MM:ss>");
            log.info("Current D&T: "+ currentDateTime.toString());
            sensors = objectMapper.readValue(msg, new TypeReference<List<Sensor>>() {});
            log.info("Sensor[0] Data:" + sensors.get(0).toString());
//            for (Sensor sensor : sensors) {
//                sensorDataService.saveSensorData(new SensorData(sensor.getSensorId(), currentDateTime, sensor.getsensorValue()));
//            }
            log.info("Data published to MongoDB");
        } catch (JsonProcessingException | ParseException e){
            throw new RuntimeException(e);
        }
    }
}
