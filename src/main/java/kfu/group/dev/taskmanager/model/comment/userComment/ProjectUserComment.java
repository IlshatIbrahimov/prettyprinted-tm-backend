package kfu.group.dev.taskmanager.model.comment.userComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserComment extends UserComment {

    @ManyToOne
    @JsonIgnore
    private Project project;

    @Builder
    public ProjectUserComment(String message, Project project, User author) {
        super(message, author);
        this.project = project;
    }
}
