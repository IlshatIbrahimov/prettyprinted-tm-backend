package kfu.group.dev.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/testAuth")
    private String testAuth() {
        return "Hi, you are authenticated!";
    }
}
