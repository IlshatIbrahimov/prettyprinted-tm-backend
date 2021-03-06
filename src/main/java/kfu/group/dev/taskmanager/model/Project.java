package kfu.group.dev.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.comment.Comment;
import kfu.group.dev.taskmanager.model.comment.userComment.ProjectUserComment;
import kfu.group.dev.taskmanager.model.listener.ProjectListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(ProjectListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ProjectUserComment> userComments = new ArrayList<>(0);

    @OneToMany(
        mappedBy = "project",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnore
    private List<Task> taskList = new ArrayList<>();

    @JsonProperty("comments")
    public List<Comment> getComments() {

        List<Comment> comments = new ArrayList<>();

        if (userComments != null && !userComments.isEmpty()) {
            comments.addAll(userComments);
        }

        Collections.sort(comments);
        return comments;
    }

    @JsonProperty("tasks")
    public List<Task> getTasks() {
        if (taskList == null) {
            return Collections.emptyList();
        }
        return taskList;
    }
}
