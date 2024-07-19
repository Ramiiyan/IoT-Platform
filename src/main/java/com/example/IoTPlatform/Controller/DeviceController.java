package com.example.IoTPlatform.Controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.example.IoTPlatform.Socket.SocketClient;
import com.example.IoTPlatform.model.Device;
import com.example.IoTPlatform.repository.DeviceRepository;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    static SocketIOServer server;
//    static private Socket socket;

    @GetMapping("/healthcheck")
    public String healthcheck(@RequestParam(value = "name", defaultValue = "World") String name){

        return String.format("Hello %s!", name);
    }
    @GetMapping("/testClient")
    public String testClient(@RequestParam(value = "name", defaultValue = "World") String name){

        //client();

        JSONObject sensorData = new JSONObject();
        sensorData.put("sensorName", "S0102");
        sensorData.put("sensorType", "Soil Moisture");
        sensorData.put("sensorValue", 77);

        /* This is the Websocket Server Message data format */
        JSONObject testJsonObj = new JSONObject();
        testJsonObj.put("type", "CLIENT");
        testJsonObj.put("room", "a");
        testJsonObj.put("message", "sensorData");
        log.info("Client connection processing...");

        SocketClient socketClient = new SocketClient();
        socketClient.socketInit();
        socketClient.publishSocketData(testJsonObj);

        return String.format("Hello:3 %s!", name);
    }

//    public static void server() throws InterruptedException, UnsupportedEncodingException {
//        Configuration config = new Configuration();
//        config.setHostname("127.0.0.1");
//        config.setPort(9096);
//        server = new SocketIOServer(config);
//        server.addEventListener("toServer", String.class, new DataListener<String>() {
//            @Override
//            public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
//                log.info("server() => server.addEventListener().onData()");
//                log.info("data: {}", data);
//                for(int i =0 ; i<10; i++)
//                    client.sendEvent("toClient", "message from server");
//            }
//        });
//        server.start();
//        //Thread.sleep(Integer.MAX_VALUE);
//        //server.stop();
//    }

    /*Client function working...*/
    public static void client() throws URISyntaxException, InterruptedException {

        JSONObject testJsonObj = new JSONObject();
        testJsonObj.put("type", "CLIENT");
        testJsonObj.put("room", "a");
        testJsonObj.put("message", "This is Client call client()");

//        socket = IO.socket("http://localhost:" + 9092 + "/?room=a");
//        socket.connect();
//        log.info("socket.connect()");
//        socket.emit("send_message", testJsonObj);
//        log.info("Data passed.");
//        log.info("connected(): {}", socket.isActive());
//        log.info("connected(): {}", socket.connected());

//        socket.on("sensor_data_from_socket", new Emitter.Listener() {
//            @Override
//            public void call(Object... objects) {
//                socket.emit("toServer", testJsonObj);
//                socket.send("test");
//            }
//        });
//        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//            @Override
//            public void call(Object... objects) {
//                socket.emit("toServer", "connected");
//                socket.send("test");
//            }
//        });
//        socket.on("toClient", new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                System.out.println("Client recievd : " + args[0]);
//
//            }
//        });
//        socket.connect();
//        while (!socket.connected())
//            Thread.sleep(50);
//        socket.send("another test");
//        Thread.sleep(10000);
//        socket.disconnect();
    }

    @PostMapping("/addDevice")
    public Device addDevice(@RequestBody Device device){
        deviceRepository.save(device);

        return device;
    }

    @GetMapping("/devices")
    public List<Device> listDevices(){
        return deviceRepository.findAll();
    }

    @PutMapping("/{deviceId}")
    public Device updateDevice(@RequestBody Device device, @PathVariable String deviceId) {
        device.setDeviceId(deviceId);
        deviceRepository.save(device);
        return device;
    }

    @DeleteMapping("/{deviceId}")
    public String deleteDevice(@PathVariable String deviceId) {
        deviceRepository.deleteById(deviceId);
        return deviceId;
    }

    @GetMapping("/{deviceName}")
    public List<Device> findDeviceByName(@PathVariable String deviceName) {
        return deviceRepository.findItemByDeviceName(deviceName);
    }

//    @GetMapping("/publish")
//    public String publisher(@RequestParam(value = "payload", defaultValue = "{\"test\":\"passed\"}") String payload){
//
//        try {
//            mqttPublisher.publish("mytopic2", payload);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return String.format("Published Payload: %s!", payload);
//    }

//    @GetMapping("/subscribe/{topic}")
//    public String subscriber(@PathVariable String topic) {
//        double value;
//        try {
//            System.out.println("get-subscribe topic:" + topic);
//
//            value = MqttSubscriber.getJsonPayload().getDouble("value");
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return String.format("Subscribed Topic: %s \n Payload: %f", topic, value);
//    }

}
