package kfu.group.dev.taskmanager.model.dialog;

import kfu.group.dev.taskmanager.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<User> users;

    @OneToMany(
        mappedBy = "dialog",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PrivateMessage> privateMessages = new ArrayList<>();
}
