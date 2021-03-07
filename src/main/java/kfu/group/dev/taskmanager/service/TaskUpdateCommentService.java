package kfu.group.dev.taskmanager.service;

import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.attribute.TaskPriority;
import kfu.group.dev.taskmanager.model.attribute.TaskStatus;
import kfu.group.dev.taskmanager.model.attribute.TaskType;
import kfu.group.dev.taskmanager.model.comment.updateComment.TaskAssigneeUpdateComment;
import kfu.group.dev.taskmanager.model.comment.updateComment.TaskPriorityUpdateComment;
import kfu.group.dev.taskmanager.model.comment.updateComment.TaskStatusUpdateComment;
import kfu.group.dev.taskmanager.model.comment.updateComment.TaskTypeUpdateComment;
import kfu.group.dev.taskmanager.repository.comment.TaskUpdateCommentRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateCommentService {

    final TaskUpdateCommentRepo taskUpdateCommentRepo;

    public TaskUpdateCommentService(TaskUpdateCommentRepo taskUpdateCommentRepo) {
        this.taskUpdateCommentRepo = taskUpdateCommentRepo;
    }

    public TaskStatusUpdateComment createTaskStatusUpdateComment(
        Task task,
        TaskStatus from,
        TaskStatus to,
        User author
    ) {
        return TaskStatusUpdateComment.builder()
            .task(task)
            .from(from)
            .to(to)
            .author(author)
            .build();
    }

    public TaskPriorityUpdateComment createTaskPriorityUpdateComment(
        Task task,
        TaskPriority from,
        TaskPriority to,
        User author
    ) {
        return TaskPriorityUpdateComment.builder()
            .task(task)
            .from(from)
            .to(to)
            .author(author)
            .build();
    }

    public TaskTypeUpdateComment createTaskTypeUpdateComment(
        Task task,
        TaskType from,
        TaskType to,
        User author
    ) {
        return TaskTypeUpdateComment.builder()
            .task(task)
            .from(from)
            .to(to)
            .author(author)
            .build();
    }

    public TaskAssigneeUpdateComment createTaskAssigneeUpdateComment(
        Task task,
        User from,
        User to,
        User author
    ) {
        return TaskAssigneeUpdateComment.builder()
            .task(task)
            .from(from)
            .to(to)
            .author(author)
            .build();
    }
}
