import java.util.HashMap;

public class StudentLiederManager {
    private final HashMap<Integer, Student> studentLiederMap;

    public StudentLiederManager() {
        studentLiederMap = new HashMap<>();
    }

    public void addStudentLieder(int studentLieder, Student student) {
        studentLiederMap.put(studentLieder, student);
    }

    public Student getStudentLieder(int studentLiederId) {
        return studentLiederMap.get(studentLiederId);
    }

    public void removeStudentLieder(int studentId) {
        studentLiederMap.remove(studentId);
    }

    public HashMap<Integer, Student> getStudentsLieder() {
        return studentLiederMap;
    }
}
