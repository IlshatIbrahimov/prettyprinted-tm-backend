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
    String name;
    @NotNull
    Integer priorityId;
    @NotNull
    Long projectId;
    @NotNull
    Long assigneeId;
    @NotNull
    Integer typeId;
    @NotNull
    Integer statusId;
    @NotNull
    String content;
}
