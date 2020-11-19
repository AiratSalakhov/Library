package com.ajrat.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MVCController {
    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/newelement")
    public String createNewElement() {
        return "newelement";
    }

    @GetMapping("/requestHeaders")
    @ResponseBody
    public String testIntegerId(@RequestHeader Map<String, String> headers) {
        headers.forEach(
                (key, value) -> log.info(String.format("Header '%s' = %s", key, value))
        );
        return headers.toString();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
