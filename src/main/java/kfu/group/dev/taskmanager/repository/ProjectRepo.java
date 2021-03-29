package kfu.group.dev.taskmanager.repository;

import kfu.group.dev.taskmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    Project findById(long id);
    Project findByName(String name);

    List<Project> findAll();
}
