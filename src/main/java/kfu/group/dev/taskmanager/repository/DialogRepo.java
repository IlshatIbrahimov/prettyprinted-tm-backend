package kfu.group.dev.taskmanager.repository;

import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.dialog.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialogRepo extends JpaRepository<Dialog, Long> {

    Dialog findByUsersContainsAndUsersContains(User firstUser, User secondUser);

}
