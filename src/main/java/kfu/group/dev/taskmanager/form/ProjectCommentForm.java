package kfu.group.dev.taskmanager.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCommentForm {

    @NotBlank
    private String content;

    @NotNull
    private Long id;
}
