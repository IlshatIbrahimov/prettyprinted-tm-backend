package kfu.group.dev.taskmanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.attribute.TaskPriority;
import kfu.group.dev.taskmanager.model.attribute.TaskStatus;
import kfu.group.dev.taskmanager.model.attribute.TaskType;
import kfu.group.dev.taskmanager.model.comment.Comment;
import kfu.group.dev.taskmanager.model.comment.updateComment.TaskUpdateComment;
import kfu.group.dev.taskmanager.model.comment.userComment.TaskUserComment;
import kfu.group.dev.taskmanager.model.listener.TaskListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(TaskListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TaskUpdateComment> updateComments;

    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TaskUserComment> userComments;

    @ManyToOne
    @JsonIgnore
    private Project project;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskPriority priority;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskStatus status;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private TaskType type;

    @ManyToOne
    private User author;

    @ManyToOne
    @NotNull
    private User assignee;

    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonProperty("project")
    public Map<String, Object> getJsonProject() {
        return Map.ofEntries(
            Map.entry("name", project.getName()),
            Map.entry("id", project.getId())
        );
    }

    @JsonInclude
    @JsonProperty("status")
    public Map<String, Object> getJsonStatus() {
        return Map.ofEntries(
            Map.entry("name", status.getName()),
            Map.entry("id", status.getId())
        );
    }

    @JsonInclude
    @JsonProperty("type")
    public Map<String, Object> getJsonType() {
        return Map.ofEntries(
            Map.entry("name", type.getName()),
            Map.entry("id", type.getId())
        );
    }

    @JsonProperty("priority")
    public Map<String, Object> getJsonPriority() {
        return Map.ofEntries(
            Map.entry("name", priority.getName()),
            Map.entry("id", priority.getId())
        );
    }

    @JsonProperty("comments")
    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();

        if (userComments != null && !userComments.isEmpty()) {
            comments.addAll(userComments);
        }

        if (updateComments != null && !updateComments.isEmpty()) {
            comments.addAll(updateComments);
        }

        Collections.sort(comments);
        return comments;
    }

    @Override
    public String toString() {
        return name;
    }
}
