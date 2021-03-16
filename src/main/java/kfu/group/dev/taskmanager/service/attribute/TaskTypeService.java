package kfu.group.dev.taskmanager.service.attribute;

import kfu.group.dev.taskmanager.model.attribute.TaskType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TaskTypeService {

    private static final String NOT_FOUND_BY_ID_MESSAGE = "Wrong type id";
    private static final String NOT_FOUND_BY_NAME_MESSAGE = "Wrong type name";

    public TaskType findById(long id) {
        Optional<TaskType> result = TaskType.getTypeList().stream().filter(type -> type.getId() == id).findAny();
        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                NOT_FOUND_BY_ID_MESSAGE
            );
        }
        return result.get();
    }

    public TaskType findByName(String name) {
        Optional<TaskType> result = TaskType.getTypeList().stream().filter(type -> type.getName().equals(name)).findAny();
        if (result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                NOT_FOUND_BY_NAME_MESSAGE
            );
        }
        return result.get();
    }
}
