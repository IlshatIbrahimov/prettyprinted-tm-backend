package kfu.group.dev.taskmanager.model.comment.updateComment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.comment.Comment;
import kfu.group.dev.taskmanager.model.comment.CommentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class TaskUpdateComment extends Comment {

    @JsonProperty
    private final CommentType type = CommentType.UPDATE_COMMENT;

    @ManyToOne
    @JsonIgnore
    private Task task;

    public TaskUpdateComment(Task task, User author) {
        super(author);
        this.task = task;
    }
}
