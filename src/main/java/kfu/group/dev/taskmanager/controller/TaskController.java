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
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    private static final Map<String, String> SUCCESS_DELETE_MESSAGE =
        Map.ofEntries(Map.entry("message", "The task have been deleted!"));

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
    private ResponseEntity<?> addTask(@Valid @RequestBody TaskForm taskForm, Authentication authentication) {
        return ResponseEntity.ok(
            taskService.addTask(taskForm, authentication)
        );
    }

    @PutMapping
    private ResponseEntity<?> updateTask(@Valid @RequestBody TaskUpdateForm taskUpdateForm, Authentication authentication) {
        return ResponseEntity.ok(
            taskService.updateTask(taskUpdateForm, authentication)
        );
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(SUCCESS_DELETE_MESSAGE);
    }
}
