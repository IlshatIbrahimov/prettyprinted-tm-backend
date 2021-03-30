package kfu.group.dev.taskmanager.service;

import kfu.group.dev.taskmanager.form.PrivateMessageForm;
import kfu.group.dev.taskmanager.model.User;
import kfu.group.dev.taskmanager.model.dialog.Dialog;
import kfu.group.dev.taskmanager.model.dialog.PrivateMessage;
import kfu.group.dev.taskmanager.repository.DialogRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DialogService {

    private static final String MESSAGE_ADDED_MESSAGE = "The private message has been added!";
    private static final String DIALOG_DOESNT_EXIST_MESSAGE = "Such a dialog doesn't exist!";
    private static final String MESSAGE_CANT_BE_ADDED_TO_YOUR_DIALOG_MESSAGE = "Can't send the message to yourself!";

    private final DialogRepo dialogRepo;
    private final UserService userService;
    private final WebSocketService webSocketService;

    public DialogService(DialogRepo dialogRepo, UserService userService, WebSocketService webSocketService) {
        this.dialogRepo = dialogRepo;
        this.userService = userService;
        this.webSocketService = webSocketService;
    }

    public ResponseEntity<?> getDialogWithUserById(long id, Authentication authentication) {
        User first = userService.getUser(id);
        User second = userService.getUser(authentication);

        if (first.getId() == second.getId()) {
            return ResponseEntity.status(400).body(MESSAGE_CANT_BE_ADDED_TO_YOUR_DIALOG_MESSAGE);
        }

        Dialog dialog = dialogRepo.findByUsersContainsAndUsersContains(first, second);

        if (dialog != null) {
            return ResponseEntity.ok(dialog);
        }

        return ResponseEntity.ok(createDialogForUsers(first, second));
    }

    private Dialog createDialogForUsers(User... users) {
        Dialog dialog = Dialog.builder()
            .privateMessages(Collections.emptyList())
            .users(List.of(users))
            .build();
        dialogRepo.save(dialog);
        return dialog;
    }

    public ResponseEntity<?> addMessage(PrivateMessageForm privateMessageForm, Authentication authentication) {

        Optional<Dialog> optionalDialog = dialogRepo.findById(privateMessageForm.getDialogId());

        if (optionalDialog.isEmpty()) {
            return ResponseEntity.status(404).body(DIALOG_DOESNT_EXIST_MESSAGE);
        }

        Dialog dialog = optionalDialog.get();

        User from = userService.getUser(authentication);
        List<User> users = new ArrayList<>(dialog.getUsers());
        users.remove(from);
        User to = users.get(0);

        if (from.getId() == to.getId()) {
            return ResponseEntity.status(400).body(MESSAGE_CANT_BE_ADDED_TO_YOUR_DIALOG_MESSAGE);
        }

        PrivateMessage privateMessage = PrivateMessage.builder()
            .dialog(dialog)
            .message(privateMessageForm.getMessage())
            .from(from)
            .to(to)
            .build();

        List<PrivateMessage> privateMessages = dialog.getPrivateMessages();
        privateMessages.add(privateMessage);
        dialog.setPrivateMessages(privateMessages);

        dialogRepo.save(dialog);
        webSocketService.newMessageInDialog(dialog);

        return ResponseEntity.ok(MESSAGE_ADDED_MESSAGE);
    }
}
