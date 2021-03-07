package kfu.group.dev.taskmanager.model.comment.userComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserComment extends UserComment {

    @ManyToOne
    @JsonIgnore
    private Task task;

    @Builder
    public TaskUserComment(String message, Task task, User author) {
        super(message, author);
        this.task = task;
    }
}
