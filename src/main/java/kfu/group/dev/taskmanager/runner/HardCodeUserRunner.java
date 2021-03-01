package kfu.group.dev.taskmanager.runner;

import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HardCodeUserRunner implements CommandLineRunner {

    final PasswordEncoder passwordEncoder;
    final UserRepo userRepo;

    public HardCodeUserRunner(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
            .email("admin@mail.ru")
            .password(passwordEncoder.encode("12345678"))
            .name("Admin")
            .surname("Admin")
            .build();

        userRepo.save(user);
    }
}
