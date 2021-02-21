package kfu.group.dev.taskmanager.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskForm {

    @NotBlank
    String taskName;
    @NotNull
    Integer priority;
    @NotNull
    Long projectId;
    @NotNull
    Long assigneeId;
    @NotNull
    Integer type;
    @NotNull
    Integer status;
    @NotBlank
    String content;
}
