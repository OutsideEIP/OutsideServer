package com.outside;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
@Import({
        AuthLogin.class,
        AuthRegister.class
})
public class OutsideApplication {

	private static final Logger log = LogManager.getLogger(OutsideApplication.class);

    @GetMapping("/")
	public String index() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(OutsideApplication.class, args);
	}

}
