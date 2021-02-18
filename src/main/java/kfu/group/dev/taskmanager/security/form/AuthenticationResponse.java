package kfu.group.dev.taskmanager.security.form;

import kfu.group.dev.taskmanager.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwt;
    private final User user;
}
