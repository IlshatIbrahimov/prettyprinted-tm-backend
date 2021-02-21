package kfu.group.dev.taskmanager.repository;

import kfu.group.dev.taskmanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
