package kfu.group.dev.taskmanager.model.listener;

import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;

@Component
public class UserListener {

    private static WebSocketService webSocketService;

    @Autowired
    public void setWebSocketService(WebSocketService webSocketService) {
        UserListener.webSocketService = webSocketService;
    }

    @PostPersist
    public void postPersist(User user) {
        webSocketService.userCreated(user);
    }
}
