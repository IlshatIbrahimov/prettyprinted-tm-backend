package kfu.group.dev.taskmanager.controller;

import kfu.group.dev.taskmanager.form.ProjectForm;
import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    private ResponseEntity<?> addProject(@Valid @RequestBody ProjectForm projectForm) {
        projectService.addProject(projectForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    private Project getProject(@PathVariable long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/{id}/tasks")
    private List<Task> getProjectTasks(@PathVariable long id) {
        return projectService.getProjectTasks(id);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
}
