package kfu.group.dev.taskmanager.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateForm {

    @NotNull
    Long id;

    @NotBlank
    String name;

    @NotNull
    Integer priorityId;

    @NotNull
    Integer statusId;

    @NotNull
    Integer typeId;

    @NotNull
    Long assigneeId;

    @NotBlank
    String content;
}
