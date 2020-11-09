package com.ajrat.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
