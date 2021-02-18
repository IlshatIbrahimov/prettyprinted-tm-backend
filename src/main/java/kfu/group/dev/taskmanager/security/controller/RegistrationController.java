package kfu.group.dev.taskmanager.security.controller;

import kfu.group.dev.taskmanager.security.form.RegistrationForm;
import kfu.group.dev.taskmanager.security.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationForm registrationForm) {
        return registrationService.register(registrationForm);
    }
}
