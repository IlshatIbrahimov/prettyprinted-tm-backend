package kfu.group.dev.taskmanager.model.attribute;

import java.util.List;

public enum TaskPriority {

    MINOR(0, "Minor"),
    NORMAL(1, "Normal"),
    MAJOR(2, "Major"),
    CRITICAL(3, "Critical");

    private static final List<TaskPriority> priorityList;

    static {
        priorityList = List.of(
            MINOR, NORMAL, MAJOR, CRITICAL
        );
    }

    private final long id;
    private final String name;

    TaskPriority(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<TaskPriority> getPriorityList() {
        return priorityList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
