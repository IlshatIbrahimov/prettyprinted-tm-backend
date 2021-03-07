package kfu.group.dev.taskmanager.model.comment.updateComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.attribute.TaskType;
import kfu.group.dev.taskmanager.model.comment.UpdateType;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Map;

@Entity
@NoArgsConstructor
public class TaskTypeUpdateComment extends TaskUpdateComment {

    @JsonProperty
    @JsonIgnore
    private final UpdateType updateType = UpdateType.TYPE_UPDATE;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskType updateFrom;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskType updateTo;

    @Builder
    public TaskTypeUpdateComment(Task task, TaskType from, TaskType to, User author) {
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