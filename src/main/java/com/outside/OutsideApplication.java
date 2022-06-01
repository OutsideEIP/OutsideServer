package com.outside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OutsideApplication {

	private static void configureLoggers() {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("./src/main/resources/logging.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		OutsideApplication.configureLoggers();

		DatabaseManager dbm = new DatabaseManager();
		dbm.setUsername("...");
		dbm.setPassword("...");
		dbm.setUrl("jdbc:postgresql://localhost:5432/outside");
		dbm.verifyState();

		SpringApplication.run(OutsideApplication.class, args);
	}

}
