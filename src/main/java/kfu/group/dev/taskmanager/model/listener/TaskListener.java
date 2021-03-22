package kfu.group.dev.taskmanager.model.listener;

import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class TaskListener {

    private static WebSocketService webSocketService;

    @Autowired
    public void setWebSocketService(WebSocketService webSocketService) {
        TaskListener.webSocketService = webSocketService;
    }

    @PostRemove
    public void postRemove(Task task) {
        webSocketService.taskDeleted(task);
    }

    @PostUpdate
    public void postUpdate(Task task) {
        webSocketService.taskUpdated(task);
    }

    @PostPersist
    public void postPersist(Task task) {
        webSocketService.taskCreated(task);
    }
}
