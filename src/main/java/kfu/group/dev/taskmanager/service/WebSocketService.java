package kfu.group.dev.taskmanager.service;

import kfu.group.dev.taskmanager.model.Project;
import kfu.group.dev.taskmanager.model.Task;
import kfu.group.dev.taskmanager.model.User;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private static final String EMPTY_MESSAGE = "";

    private static final String PROJECT_UPDATE_URL = "/project/update/";
    private static final String PROJECT_CREATE_URL = "/project/create/";
    private static final String PROJECT_DELETE_URL = "/project/delete/";

    private static final String TASK_UPDATE_URL = "/task/update/";
    private static final String TASK_CREATE_URL = "/task/create/";
    private static final String TASK_DELETE_URL = "/task/delete/";

    private static final String PROJECT_MAIN_URL = "/project/";
    private static final String TASK_ASSIGNEE_URL = "/user/assignee/";
    private static final String USER_REGISTERED_URL = "/user/registered/";

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void projectCreated(Project project) {
        sendMessageToDestination(PROJECT_CREATE_URL + project.getId());
        sendMessageToDestination(PROJECT_MAIN_URL);
    }

    public void projectUpdated(Project project) {
        sendMessageToDestination(PROJECT_UPDATE_URL + project.getId());
        sendMessageToDestination(PROJECT_MAIN_URL);
    }

    public void projectDeleted(Project project) {
        sendMessageToDestination(PROJECT_DELETE_URL + project.getId());
        sendMessageToDestination(PROJECT_MAIN_URL);
    }

    public void taskCreated(Task task) {
        sendMessageToDestination(TASK_CREATE_URL + task.getId());
        sendMessageToDestination(PROJECT_UPDATE_URL + task.getProject().getId());
        User assignee = task.getAssignee();
        if (assignee == null) {
            return;
        }
        assigneeChanged(assignee);
    }

    public void taskUpdated(Task task) {
        sendMessageToDestination(TASK_UPDATE_URL + task.getId());
        sendMessageToDestination(PROJECT_UPDATE_URL + task.getProject().getId());
        User assignee = task.getAssignee();
        if (assignee == null) {
            return;
        }
        assigneeChanged(assignee);
    }

    public void taskDeleted(Task task) {
        sendMessageToDestination(TASK_DELETE_URL + task.getId());
        sendMessageToDestination(PROJECT_UPDATE_URL + task.getProject().getId());
        User assignee = task.getAssignee();
        if (assignee == null) {
            return;
        }
        assigneeChanged(assignee);
    }

    public void userCreated(User user) {
        sendMessageToDestination(USER_REGISTERED_URL);
    }

    public void assigneeChanged(User assignee) {
        sendMessageToDestination(TASK_ASSIGNEE_URL + assignee.getId());
    }

    private void sendMessageToDestination(String destination) {
        simpMessagingTemplate.convertAndSend(destination, EMPTY_MESSAGE);
    }
}
