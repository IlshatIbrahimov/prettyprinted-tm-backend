package kfu.group.dev.taskmanager.security.service;


import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bad credentials");
        }

        return new CustomUserDetails(user);
    }
}
