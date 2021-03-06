package kfu.group.dev.taskmanager.service;

import kfu.group.dev.taskmanager.form.TaskForm;
import kfu.group.dev.taskmanager.form.TaskUpdateForm;
import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.attribute.TaskPriority;
import kfu.group.dev.taskmanager.model.attribute.TaskStatus;
import kfu.group.dev.taskmanager.model.attribute.TaskType;
import kfu.group.dev.taskmanager.repository.TaskRepo;
import kfu.group.dev.taskmanager.service.attribute.TaskPriorityService;
import kfu.group.dev.taskmanager.service.attribute.TaskStatusService;
import kfu.group.dev.taskmanager.service.attribute.TaskTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskStatusService taskStatusService;
    private final TaskPriorityService taskPriorityService;
    private final TaskTypeService taskTypeService;

    public TaskService(TaskRepo taskRepo, UserService userService, ProjectService projectService, TaskStatusService taskStatusService, TaskPriorityService taskPriorityService, TaskTypeService taskTypeService) {
        this.taskRepo = taskRepo;
        this.userService = userService;
        this.projectService = projectService;
        this.taskStatusService = taskStatusService;
        this.taskPriorityService = taskPriorityService;
        this.taskTypeService = taskTypeService;
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public List<Task> getProjectTasks(Project project) {
        return taskRepo.findAllByProject(project);
    }

    public List<Task> getUserTasks(Authentication authentication) {
        return taskRepo.findAllByAssignee(userService.getUser(authentication));
    }

    public Task getTaskById(long id) {
        Optional<Task> task = taskRepo.findById(id);
        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with ID = " + id + " doesn't exist!");
        }
        return task.get();
    }

    public void addTask(TaskForm taskForm, Authentication authentication) {

        User assignee = userService.getUser(taskForm.getAssigneeId());
        User author = userService.getUser(authentication);
        Project project = projectService.getProjectById(taskForm.getProjectId());
        TaskStatus status = taskStatusService.findById(taskForm.getStatusId());
        TaskType type = taskTypeService.findById(taskForm.getTypeId());
        TaskPriority priority = taskPriorityService.findById(taskForm.getPriorityId());

        Task task = Task.builder()
            .name(taskForm.getTaskName())
            .content(taskForm.getContent())
            .assignee(assignee)
            .author(author)
            .priority(priority)
            .status(status)
            .project(project)
            .type(type)
            .build();

        taskRepo.save(task);
    }

    public void updateTask(TaskUpdateForm taskUpdateForm) {

        Optional<Task> optionalTask = taskRepo.findById(taskUpdateForm.getId());

        if (optionalTask.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id = " + taskUpdateForm.getId() + " doesn't exist!");
        }

        User assignee = userService.getUser(taskUpdateForm.getAssigneeId());
        TaskStatus status = taskStatusService.findById(taskUpdateForm.getStatusId());
        TaskType type = taskTypeService.findById(taskUpdateForm.getTypeId());
        TaskPriority priority = taskPriorityService.findById(taskUpdateForm.getPriorityId());

        Task task = optionalTask.get();
        task.setName(taskUpdateForm.getName());
        task.setPriority(priority);
        task.setStatus(status);
        task.setType(type);
        task.setAssignee(assignee);
        task.setContent(taskUpdateForm.getContent());

        taskRepo.save(task);
    }

    public void deleteTask(long id) {
        Optional<Task> task = taskRepo.findById(id);
        if (task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id = " + id + " doesn't exist!");
        }

        taskRepo.delete(task.get());
    }
}
