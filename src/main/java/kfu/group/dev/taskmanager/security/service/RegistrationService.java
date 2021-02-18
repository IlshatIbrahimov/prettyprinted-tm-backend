package kfu.group.dev.taskmanager.security.service;


import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.repository.UserRepo;
import kfu.group.dev.taskmanager.security.form.AuthenticationResponse;
import kfu.group.dev.taskmanager.security.form.RegistrationForm;
import kfu.group.dev.taskmanager.security.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    final JwtUtil jwtUtil;
    final UserRepo userRepo;
    final CustomUserDetailsService customUserDetailsService;
    final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepo userRepo, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> register(RegistrationForm registrationForm) {

        if (userRepo.findByEmail(registrationForm.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Email address has been already registered");
        }

        User user = User.builder()
            .email(registrationForm.getEmail())
            .password(passwordEncoder.encode(registrationForm.getPassword()))
            .name(registrationForm.getName())
            .surname(registrationForm.getSurname()).build();

        userRepo.save(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, user));
    }
}
