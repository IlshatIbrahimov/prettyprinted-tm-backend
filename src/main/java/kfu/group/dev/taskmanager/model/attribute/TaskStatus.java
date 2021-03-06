package kfu.group.dev.taskmanager.model.attribute;

import java.util.List;

public enum TaskStatus {

    OPEN(0, "Open"),
    IN_PROGRESS(1, "In Progress"),
    FIXED(2, "Fixed"),
    WONT_FIX(3, "Won't Fix");

    private static final List<TaskStatus> statusList;

    static {
        statusList = List.of(
            OPEN, IN_PROGRESS, WONT_FIX, FIXED
        );
    }

    private final long id;
    private final String name;

    TaskStatus(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<TaskStatus> getStatusList() {
        return statusList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
