package kfu.group.dev.taskmanager.model.listener;

import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

@Component
public class ProjectListener {

    private static WebSocketService webSocketService;

    @Autowired
    public void setWebSocketService(WebSocketService webSocketService) {
        ProjectListener.webSocketService = webSocketService;
    }

    @PostRemove
    public void postRemove(Project project) {
        webSocketService.projectDeleted(project);
    }

    @PostUpdate
    public void postUpdate(Project project) {
        webSocketService.projectUpdated(project);
    }

    @PostPersist
    public void postPersist(Project project) {
        webSocketService.projectCreated(project);
    }
}
