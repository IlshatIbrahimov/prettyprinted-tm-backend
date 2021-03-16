package kfu.group.dev.taskmanager.model.comment.updateComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.comment.UpdateType;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class TaskAssigneeUpdateComment extends TaskUpdateComment {

    @JsonProperty
    @JsonIgnore
    private final UpdateType updateType = UpdateType.ASSIGNEE_UPDATE;

    @ManyToOne
    @JsonProperty
    private User updateFrom;

    @ManyToOne
    @JsonProperty
    private User updateTo;

    @Builder
    public TaskAssigneeUpdateComment(Task task, User from, User to, User author) {
        super(task, author);
        this.updateFrom = from;
        this.updateTo = to;
    }

    @JsonProperty
    public String getUpdateType() {
        return updateType.getName();
    }
}
