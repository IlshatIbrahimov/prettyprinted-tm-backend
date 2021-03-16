package kfu.group.dev.taskmanager.model.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Data
public class Comment implements Comparable<Comment> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User author;

    @Column
    private Date date;

    public Comment(User author) {
        this.author = author;
    }

    @PrePersist
    @JsonProperty
    private void prePersist() {
        date = date == null ? new Date() : date;
    }

    @Override
    public int compareTo(Comment comment) {
        return date.compareTo(comment.date);
    }
}
