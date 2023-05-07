public class Student {
    private final Integer studentId;
    private final String studentFirstName;
    private final String studentLastName;

    public Student(UniqueId uniqueIdGenerator, String studentFirstName, String studentLastName) {
        this.studentId = uniqueIdGenerator.uniqueDataId();
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public Student(Integer studentId, String studentFirstName, String studentLastName) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }
    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }
}