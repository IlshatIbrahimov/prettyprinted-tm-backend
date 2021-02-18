package kfu.group.dev.taskmanager.security.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegistrationForm {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
}
