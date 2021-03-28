package kfu.group.dev.taskmanager.model.listener;

import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class TaskListener {

    private static WebSocketService webSocketService;

    @Transient
    private transient Task beforeChangeTask;

    @Autowired
    public void setWebSocketService(WebSocketService webSocketService) {
        TaskListener.webSocketService = webSocketService;
    }

    @PrePersist
    @PreRemove
    @PreUpdate
    public void prePersistOrUpdateOrDelete(Task task) {
        this.beforeChangeTask = task;
    }

    @PostRemove
    public void postRemove(Task task) {
        webSocketService.taskDeleted(task);
        webSocketService.taskUpdated(beforeChangeTask);
    }

    @PostUpdate
    public void postUpdate(Task task) {
        webSocketService.taskUpdated(task);
        webSocketService.taskUpdated(beforeChangeTask);
    }

    @PostPersist
    public void postPersist(Task task) {
        webSocketService.taskCreated(task);
        webSocketService.taskUpdated(beforeChangeTask);
    }
}
