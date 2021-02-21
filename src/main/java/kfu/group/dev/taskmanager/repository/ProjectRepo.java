package kfu.group.dev.taskmanager.repository;

import kfu.group.dev.taskmanager.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Long> {

    Project findById(long id);
}
