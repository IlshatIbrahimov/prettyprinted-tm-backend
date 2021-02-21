package kfu.group.dev.taskmanager.controller;

import kfu.group.dev.taskmanager.form.TaskForm;
import kfu.group.dev.taskmanager.form.TaskUpdateForm;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    private Task getTask(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/user")
    private List<Task> getUserTasks(Authentication authentication) {
        return taskService.getUserTasks(authentication);
    }

    @PostMapping
    private ResponseEntity<?> addTask(@Valid TaskForm taskForm, Authentication authentication) {
        taskService.addTask(taskForm, authentication);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    private ResponseEntity<?> updateTask(@Valid TaskUpdateForm taskUpdateForm) {
        taskService.updateTask(taskUpdateForm);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
