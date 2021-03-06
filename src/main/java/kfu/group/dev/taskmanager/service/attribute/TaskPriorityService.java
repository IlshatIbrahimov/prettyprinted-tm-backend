package kfu.group.dev.taskmanager.service.attribute;

import kfu.group.dev.taskmanager.model.attribute.TaskPriority;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TaskPriorityService {

    private static final String NOT_FOUND_BY_ID_MESSAGE = "Wrong priority id";
    private static final String NOT_FOUND_BY_NAME_MESSAGE = "Wrong priority name";

    public TaskPriority findById(long id) {
        Optional<TaskPriority> result = TaskPriority.getPriorityList().stream().filter(priority -> priority.getId() == id).findAny();
        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                NOT_FOUND_BY_ID_MESSAGE
            );
        }
        return result.get();
    }

    public TaskPriority findByName(String name) {
        Optional<TaskPriority> result = TaskPriority.getPriorityList().stream().filter(priority -> priority.getName().equals(name)).findAny();
        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                NOT_FOUND_BY_NAME_MESSAGE
            );
        }
        return result.get();
    }
}
