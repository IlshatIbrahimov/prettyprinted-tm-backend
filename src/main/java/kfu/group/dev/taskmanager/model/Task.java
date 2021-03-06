package kfu.group.dev.taskmanager.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.attribute.TaskPriority;
import kfu.group.dev.taskmanager.model.attribute.TaskStatus;
import kfu.group.dev.taskmanager.model.attribute.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Project project;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @ManyToOne
    private User author;

    @ManyToOne
    @NotNull
    private User assignee;

    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonProperty("project")
    public Map<String, Object> getProject() {
        return Map.ofEntries(
            Map.entry("name", project.getName()),
            Map.entry("id", project.getId())
        );
    }

    @JsonProperty("status")
    public Map<String, Object> getStatus() {
        return Map.ofEntries(
            Map.entry("name", status.getName()),
            Map.entry("id", status.getId())
        );
    }

    @JsonProperty("type")
    public Map<String, Object> getType() {
        return Map.ofEntries(
            Map.entry("name", type.getName()),
            Map.entry("id", type.getId())
        );
    }

    @JsonProperty("priority")
    public Map<String, Object> getPriority() {
        return Map.ofEntries(
            Map.entry("name", priority.getName()),
            Map.entry("id", priority.getId())
        );
    }
}
