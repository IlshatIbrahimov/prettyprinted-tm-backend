package kfu.group.dev.taskmanager.model.attribute;

import java.util.List;

public enum TaskType {

    BUG(0, "Bug"),
    FEATURE(1, "Feature"),
    QUESTION(2, "Question");

    private static final List<TaskType> typeList;

    static {
        typeList = List.of(
            BUG, FEATURE, QUESTION
        );
    }

    private final long id;
    private final String name;

    TaskType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<TaskType> getTypeList() {
        return typeList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
