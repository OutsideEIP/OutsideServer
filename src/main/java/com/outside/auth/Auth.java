package com.outside;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Import;

@RequestMapping("/auth")
@Import({
        AuthLogin.class,
        AuthRegister.class
})
@RestController
public class Auth {

}
