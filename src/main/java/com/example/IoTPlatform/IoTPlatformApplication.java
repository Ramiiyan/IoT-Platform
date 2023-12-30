package com.example.IoTPlatform;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
@EnableMongoRepositories
public class IoTPlatformApplication implements CommandLineRunner {

	@Autowired
	private SocketIOServer socketIOServer;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(IoTPlatformApplication.class, args);
//		ApplicationContext applicationContext = SpringApplication.run(IoTPlatformApplication.class, args);
//
//		SocketIOServer socketIOServer = applicationContext.getBean(SocketIOServer.class);
//		socketIOServer.start();
	}

	@Override
	public void run(String... args) throws Exception {
		socketIOServer.start();
	}

}
