package kfu.group.dev.taskmanager.security.controller;

import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.repository.UserRepo;
import kfu.group.dev.taskmanager.security.form.AuthenticationRequest;
import kfu.group.dev.taskmanager.security.form.AuthenticationResponse;
import kfu.group.dev.taskmanager.security.service.CustomUserDetailsService;
import kfu.group.dev.taskmanager.security.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);

        User user = userRepo.findByEmail(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
    }
}
