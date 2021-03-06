package kfu.group.dev.taskmanager.service.attribute;

import kfu.group.dev.taskmanager.model.attribute.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TaskStatusService {

    private static final String NOT_FOUND_BY_ID_MESSAGE = "Wrong status id";
    private static final String NOT_FOUND_BY_NAME_MESSAGE = "Wrong status name";

    public TaskStatus findById(long id) {
        Optional<TaskStatus> result = TaskStatus.getStatusList().stream().filter(status -> status.getId() == id).findAny();
        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                NOT_FOUND_BY_ID_MESSAGE
            );
        }
        return result.get();
    }

    public TaskStatus findByName(String name) {
        Optional<TaskStatus> result = TaskStatus.getStatusList().stream().filter(status -> status.getName().equals(name)).findAny();
        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                NOT_FOUND_BY_NAME_MESSAGE
            );
        }
        return result.get();
    }
}
