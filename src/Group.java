import java.util.List;

public class Group {

    private final Integer groupId;
    private final List<Student> studentGroup;

    public Group(Integer groupId,
                 List<Student> studentGroup

    ) {
        this.groupId = groupId;
        this.studentGroup = studentGroup;

    }

    public Integer getGroupId() {
        return groupId;
    }
    public List<Student> getStudentGroup() {
        return studentGroup;
    }

    }
