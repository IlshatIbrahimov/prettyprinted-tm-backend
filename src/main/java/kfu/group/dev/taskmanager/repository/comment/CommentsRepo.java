package kfu.group.dev.taskmanager.repository.comment;

import kfu.group.dev.taskmanager.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comment, Long> {
}
