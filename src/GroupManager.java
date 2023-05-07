import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupManager {
    private final HashMap<Integer, Group> groupMap;

    public GroupManager(){
        groupMap = new HashMap<>();
    }

    public void addStudentToGroup(Integer groupId, Student student){

            List<Student> studentGroup = new ArrayList<>();
            studentGroup.add(student);
            Group group = new Group(groupId, studentGroup);
            groupMap.put(groupId, group);

    }

    public Group getGroup(Integer groupId){
        for (Group group : groupMap.values()) {
            if (group.getGroupId().equals(groupId)) {
                return group;
            }
        }
        return null;
    }



}
