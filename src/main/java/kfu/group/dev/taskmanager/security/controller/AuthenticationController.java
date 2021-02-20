package kfu.group.dev.taskmanager.security.controller;

import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.repository.UserRepo;
import kfu.group.dev.taskmanager.security.form.AuthenticationRequest;
import kfu.group.dev.taskmanager.security.form.AuthenticationResponse;
import kfu.group.dev.taskmanager.security.service.CustomUserDetails;
import kfu.group.dev.taskmanager.security.service.CustomUserDetailsService;
import kfu.group.dev.taskmanager.security.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    final JwtUtil jwtUtil;
    final AuthenticationManager authenticationManager;
    final CustomUserDetailsService customUserDetailsService;
    final UserRepo userRepo;
    final PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        try {
            authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                    )
                );
        } catch (Exception e) {
            return ResponseEntity
                .status(401)
                .body(
                    Map.of("message", "Wrong email or password!")
                );
        }

        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);

        User user = userRepo.findByEmail(userDetails.getEmail());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
    }
}
