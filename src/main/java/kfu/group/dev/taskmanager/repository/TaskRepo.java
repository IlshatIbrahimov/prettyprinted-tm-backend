package kfu.group.dev.taskmanager.repository;

import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {

    List<Task> findAll();

    List<Task> findAllByAssignee(User user);

    List<Task> findAllByProject(Project project);
}
