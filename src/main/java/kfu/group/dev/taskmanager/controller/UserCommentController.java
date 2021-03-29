package kfu.group.dev.taskmanager.controller;

import kfu.group.dev.taskmanager.form.ProjectCommentForm;
import kfu.group.dev.taskmanager.form.TaskCommentForm;
import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.service.UserCommentService;
import kfu.group.dev.taskmanager.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class UserCommentController {

    private static final Map<String, String> SUCCESS_ADD_MESSAGE =
        Map.ofEntries(Map.entry("message", "The message have been added!"));

    private final UserCommentService userCommentService;
    private final WebSocketService webSocketService;

    public UserCommentController(UserCommentService userCommentService, WebSocketService webSocketService) {
        this.userCommentService = userCommentService;
        this.webSocketService = webSocketService;
    }

    @PostMapping("/project")
    public ResponseEntity<?> addProjectComment(@Valid @RequestBody ProjectCommentForm projectCommentForm, Authentication authentication) {
        Project project = userCommentService.addProjectComment(projectCommentForm, authentication);
        webSocketService.projectUpdated(project);
        return ResponseEntity.ok(SUCCESS_ADD_MESSAGE);
    }

    @PostMapping("/task")
    public ResponseEntity<?> addTaskComment(@Valid @RequestBody TaskCommentForm taskCommentForm, Authentication authentication) {
        Task task = userCommentService.addTaskComment(taskCommentForm, authentication);
        webSocketService.taskUpdated(task);
        return ResponseEntity.ok(SUCCESS_ADD_MESSAGE);
    }
}
