package kfu.group.dev.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kfu.group.dev.taskmanager.model.listener.UserListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(UserListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @JsonIgnore
    private String email;

    @Column
    @JsonIgnore
    private String password;
}
