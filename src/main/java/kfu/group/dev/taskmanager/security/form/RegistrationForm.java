package kfu.group.dev.taskmanager.security.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegistrationForm {

    private static final String EMAIL_REGEXP = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email(regexp = EMAIL_REGEXP, message = "Invalid email!")
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
}
