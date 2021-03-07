package kfu.group.dev.taskmanager.service;

import kfu.group.dev.taskmanager.form.ProjectCommentForm;
import kfu.group.dev.taskmanager.form.TaskCommentForm;
import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.comment.userComment.ProjectUserComment;
import kfu.group.dev.taskmanager.model.comment.userComment.TaskUserComment;
import kfu.group.dev.taskmanager.repository.ProjectRepo;
import kfu.group.dev.taskmanager.repository.TaskRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentService {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;
    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;

    public UserCommentService(ProjectService projectService, TaskService taskService, UserService userService, ProjectRepo projectRepo, TaskRepo taskRepo) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }

    public void addProjectComment(ProjectCommentForm projectCommentForm, Authentication authentication) {

        Project project = projectService.getProjectById(projectCommentForm.getId());
        User author = userService.getUser(authentication);

        ProjectUserComment userComment = ProjectUserComment.builder()
            .project(project)
            .author(author)
            .message(projectCommentForm.getContent())
            .build();

        List<ProjectUserComment> projectUserComments = project.getUserComments();
        projectUserComments.add(userComment);
        project.setUserComments(projectUserComments);

        projectRepo.save(project);
    }

    public void addTaskComment(TaskCommentForm taskCommentForm, Authentication authentication) {

        Task task = taskService.getTaskById(taskCommentForm.getId());
        User author = userService.getUser(authentication);

        TaskUserComment userComment = TaskUserComment.builder()
            .task(task)
            .author(author)
            .message(taskCommentForm.getContent())
            .build();

        List<TaskUserComment> taskUserComments = task.getUserComments();
        taskUserComments.add(userComment);
        task.setUserComments(taskUserComments);

        taskRepo.save(task);
    }
}
