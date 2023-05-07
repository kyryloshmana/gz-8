import java.util.HashMap;

public class StudentManager {
    private final HashMap<Integer, Student> studentMap;

    public StudentManager() {
        studentMap = new HashMap<>();
    }

    public HashMap<Integer, Student> getStudents() {
        return studentMap;
    }

    public void addStudent(Student student) {
        studentMap.put(student.getStudentId(), student);
    }

    public Student getStudent(int studentId) {
        return studentMap.get(studentId);
    }

    public void removeStudent(int studentId) {
        studentMap.remove(studentId);
    }
}