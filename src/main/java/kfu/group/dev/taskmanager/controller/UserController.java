package kfu.group.dev.taskmanager.controller;

import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/current")
    public User getCurrentUser(Authentication authentication) {
        return userService.getUser(authentication);
    }
}
