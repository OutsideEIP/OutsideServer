package com.outside;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class OutsideApplication {

	// private static final Logger log = LogManager.getLogger(OutsideApplication.class);

    @GetMapping("/")
	// @Schema(test = "oui")
	public String index() {
		return "Hello!";
	}

	public static void main(String[] args) {
		SpringApplication.run(OutsideApplication.class, args);
	}

}
