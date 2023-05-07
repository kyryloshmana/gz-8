import java.util.HashMap;

public class TasksManager {
    private final HashMap<Integer,String> taskMap;

    public TasksManager(){
        taskMap = new HashMap<>();
    }

    public void addTask(int id, String task){
        taskMap.put(id, task);
    }

    public String getTask (int student){
        return taskMap.get(student);
    }
}
