package kfu.group.dev.taskmanager.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProjectForm {

    @NotBlank
    private String name;

}
