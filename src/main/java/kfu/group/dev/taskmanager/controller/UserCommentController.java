package kfu.group.dev.taskmanager.controller;

import kfu.group.dev.taskmanager.form.ProjectCommentForm;
import kfu.group.dev.taskmanager.form.TaskCommentForm;
import kfu.group.dev.taskmanager.service.UserCommentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
public class UserCommentController {

    private final UserCommentService userCommentService;

    public UserCommentController(UserCommentService userCommentService) {
        this.userCommentService = userCommentService;
    }

    @PostMapping("/project")
    public void addProjectComment(@Valid @RequestBody ProjectCommentForm projectCommentForm, Authentication authentication) {
        userCommentService.addProjectComment(projectCommentForm, authentication);
    }

    @PostMapping("/task")
    public void addTaskComment(@Valid @RequestBody TaskCommentForm taskCommentForm, Authentication authentication) {
        userCommentService.addTaskComment(taskCommentForm, authentication);
    }
}
