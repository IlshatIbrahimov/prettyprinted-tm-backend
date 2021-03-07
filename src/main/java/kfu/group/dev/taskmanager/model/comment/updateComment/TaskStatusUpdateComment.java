package kfu.group.dev.taskmanager.model.comment.updateComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.attribute.TaskStatus;
import kfu.group.dev.taskmanager.model.comment.UpdateType;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Map;

@Entity
@NoArgsConstructor
public class TaskStatusUpdateComment extends TaskUpdateComment {

    @JsonProperty
    @JsonIgnore
    private final UpdateType updateType = UpdateType.STATUS_UPDATE;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskStatus updateFrom;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskStatus updateTo;

    @Builder
    public TaskStatusUpdateComment(Task task, TaskStatus from, TaskStatus to, User author) {
        super(task, author);
        this.updateFrom = from;
        this.updateTo = to;
    }

    @JsonProperty("from")
    public Map<String, Object> getUpdateFrom() {
        return Map.ofEntries(
            Map.entry("id", updateFrom.getId()),
            Map.entry("name", updateFrom.getName())
        );
    }

    @JsonProperty("to")
    public Map<String, Object> getUpdateTo() {
        return Map.ofEntries(
            Map.entry("id", updateTo.getId()),
            Map.entry("name", updateTo.getName())
        );
    }

    @JsonProperty
    public String getUpdateType() {
        return updateType.getName();
    }
}
