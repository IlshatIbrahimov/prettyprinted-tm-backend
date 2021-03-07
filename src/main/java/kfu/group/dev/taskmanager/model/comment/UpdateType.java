package kfu.group.dev.taskmanager.model.comment;

public enum UpdateType {

    STATUS_UPDATE("Status"),
    PRIORITY_UPDATE("Priority"),
    ASSIGNEE_UPDATE("Assignee"),
    TYPE_UPDATE("Type");

    private final String name;

    UpdateType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
