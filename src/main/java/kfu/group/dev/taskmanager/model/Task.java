package kfu.group.dev.taskmanager.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Project project;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotNull
    private Integer priority;

    @Column
    @NotNull
    private Integer status;

    @ManyToOne
    private User author;

    @ManyToOne
    @NotNull
    private User assignee;

    @Column
    @NotNull
    private Integer type;

    @Column(columnDefinition = "TEXT")
    private String content;
}
