package kfu.group.dev.taskmanager.model.comment.userComment;

import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.comment.Comment;
import kfu.group.dev.taskmanager.model.comment.CommentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class UserComment extends Comment {

    @JsonProperty
    private final CommentType type = CommentType.USER_COMMENT;

    @Column
    @JsonProperty
    private String message;

    public UserComment(String message, User author) {
        super(author);
        this.message = message;
    }
}
