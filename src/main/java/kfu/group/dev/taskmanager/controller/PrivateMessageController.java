package kfu.group.dev.taskmanager.controller;

import kfu.group.dev.taskmanager.form.PrivateMessageForm;
import kfu.group.dev.taskmanager.model.dialog.Dialog;
import kfu.group.dev.taskmanager.service.DialogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/private")
public class PrivateMessageController {

    private final DialogService dialogService;

    public PrivateMessageController(DialogService dialogService) {
        this.dialogService = dialogService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDialog(@PathVariable long id, Authentication authentication) {
        return dialogService.getDialogWithUserById(id, authentication);
    }

    @PostMapping
    public ResponseEntity<?> addPrivateMessage(@RequestBody @Valid PrivateMessageForm privateMessageForm,
                                               Authentication authentication) {
        return dialogService.addMessage(privateMessageForm, authentication);
    }

}
