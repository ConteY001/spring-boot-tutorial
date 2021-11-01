package de.yaxc.springboottutorial.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/welcome")
public class HelloController {

    @Value("${welcome.message}")
    private String welcomeMessage;


    @GetMapping
    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
