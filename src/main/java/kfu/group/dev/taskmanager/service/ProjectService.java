package kfu.group.dev.taskmanager.service;

import kfu.group.dev.taskmanager.form.ProjectForm;
import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.repository.ProjectRepo;
import kfu.group.dev.taskmanager.repository.TaskRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;

    public ProjectService(ProjectRepo projectRepo, TaskRepo taskRepo) {
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project getProjectById(long id) {
        Project project = projectRepo.findById(id);

        if (project == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with ID = " + id + " doesn't exist!");
        }

        return project;
    }

    public void addProject(ProjectForm projectForm) {
        Project project = Project.builder().name(projectForm.getName()).build();
        projectRepo.save(project);
    }

    public void deleteProject(long id) {
        Project project = getProjectById(id);
        projectRepo.delete(project);
    }

    public List<Task> getProjectTasks(long id) {
        Project project = getProjectById(id);
        return taskRepo.findAllByProject(project);
    }
}
