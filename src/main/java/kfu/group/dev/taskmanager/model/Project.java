package kfu.group.dev.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kfu.group.dev.taskmanager.model.comment.Comment;
import kfu.group.dev.taskmanager.model.comment.userComment.ProjectUserComment;
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
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ProjectUserComment> userComments;

    @OneToMany(
        mappedBy = "project",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Task> taskList;

    public List<ProjectUserComment> getComments() {
        Collections.sort(userComments);
        return userComments;
    }
}
