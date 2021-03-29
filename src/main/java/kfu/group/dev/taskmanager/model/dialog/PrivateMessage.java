package kfu.group.dev.taskmanager.model.dialog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import kfu.group.dev.taskmanager.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Dialog dialog;

    @ManyToOne
    private User from;

    @ManyToOne
    private User to;

    @Column(columnDefinition = "TEXT")
    @JsonProperty
    private String message;

    @Column
    private Date date;

    @PrePersist
    @JsonProperty
    private void prePersist() {
        date = date == null ? new Date() : date;
    }
}
