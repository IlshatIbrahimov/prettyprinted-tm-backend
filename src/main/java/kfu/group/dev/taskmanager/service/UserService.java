package kfu.group.dev.taskmanager.service;

import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.repository.UserRepo;
import kfu.group.dev.taskmanager.security.service.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUser(long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID = " + id + " doesn't exist!");
        }
        return user.get();
    }

    public User getUser(String email) {
        Optional<User> user = Optional.ofNullable(userRepo.findByEmail(email));
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email = " + email + " doesn't exist!");
        }
        return user.get();
    }

    public User getUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return getUser(userDetails.getEmail());
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
