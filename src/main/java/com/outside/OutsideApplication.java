package com.outside;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OutsideApplication {

	private static final Logger log = LogManager.getLogger(OutsideApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OutsideApplication.class, args);
	}

}
