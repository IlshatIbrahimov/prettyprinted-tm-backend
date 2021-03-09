package kfu.group.dev.taskmanager.controller;

import kfu.group.dev.taskmanager.form.ProjectCommentForm;
import kfu.group.dev.taskmanager.form.TaskCommentForm;
import kfu.group.dev.taskmanager.service.UserCommentService;
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

    public UserCommentController(UserCommentService userCommentService) {
        this.userCommentService = userCommentService;
    }

    @PostMapping("/project")
    public ResponseEntity<?> addProjectComment(@Valid @RequestBody ProjectCommentForm projectCommentForm, Authentication authentication) {
        userCommentService.addProjectComment(projectCommentForm, authentication);
        return ResponseEntity.ok(SUCCESS_ADD_MESSAGE);
    }

    @PostMapping("/task")
    public ResponseEntity<?> addTaskComment(@Valid @RequestBody TaskCommentForm taskCommentForm, Authentication authentication) {
        userCommentService.addTaskComment(taskCommentForm, authentication);
        return ResponseEntity.ok(SUCCESS_ADD_MESSAGE);
    }
}
