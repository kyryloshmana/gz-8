import java.util.*;

public class Main {
    static boolean check = false;
    static StudentManager studentManager = new StudentManager();
    static GroupManager groupManager = new GroupManager();
    static StudentLiederManager studentLiederManager = new StudentLiederManager();
    static TasksManager tasksManager = new TasksManager();
        public static void main(String[] args) {

        Scanner data = new Scanner(System.in);

        String menu = """
                Меню:
                1. Додати студента
                2. Видалити студента
                3. Перейменувати студента
                4. Змінити старосту групи
                5. Створити групу
                6. Підтвердити виконання завдання
                7. Показати всю інформацію по групі
                8. Показати всіх студентів
                ***********************************************
                """;


        System.out.println(menu);
        System.out.println("Оберіть номер:");

        while (!check){
            String selectNUmber = data.nextLine();
            switch (selectNUmber) {
                case "1" -> createStudent();
                case "2" -> deleteStudent();
                case "3" -> editStudentName();
                case "4" -> changeGroupLieder();
                case "5" -> createGroup();
                case "6" -> taskDone();
                case "7" -> showGroupDetails();
                case "8" -> displayStudents();
                default -> System.out.println("No such menu");
            }
            System.out.println(menu);
        }
    }



    static void displayStudents(){
        HashMap<Integer, Student> students = studentManager.getStudents();
        for (Student studentData : students.values()){
            System.out.print(studentData.getStudentId() + " ");
            System.out.print(studentData.getStudentFirstName() + " ");
            System.out.println(studentData.getStudentLastName() + " ");
        }
        HashMap<Integer, Student> studentsLieder = studentLiederManager.getStudentsLieder();
        for (Student studentData : studentsLieder.values()){
            System.out.print(studentData.getStudentId() + " ");
            System.out.print(studentData.getStudentFirstName() + " ");
            System.out.println(studentData.getStudentLastName() + " ");
        }
    }

    static void createStudent(){
        UniqueId uniqueId = new UniqueId();
        Scanner studentFirstName = new Scanner(System.in);
        Scanner studentLastName = new Scanner(System.in);
        System.out.println("Введіть прізвище студента");
        String enteredFirstName = studentFirstName.nextLine();
        System.out.println("Введіть імя студента");
        String enteredLAstName = studentLastName.nextLine();
        Student student = new Student(uniqueId,enteredFirstName,enteredLAstName);
        studentManager.addStudent(student);

    }

   public static Integer getUniqueData(){
        UniqueId uniqueId = new UniqueId();
        return uniqueId.uniqueDataId();
    }


    static void changeGroupLieder(){
        Scanner oldGroupLieder = new Scanner(System.in);
        System.out.println("Введіть номер групи, в якій хочете змінити старосту");
        int enteredOldGroupLieder = oldGroupLieder.nextInt();

        Scanner newGroupLieder = new Scanner(System.in);
        System.out.println("Введіть номер студента, хто буде новим старостою");
        int enteredNewGroupLieder = newGroupLieder.nextInt();

        studentLiederManager.removeStudentLieder(enteredOldGroupLieder);
        studentLiederManager.addStudentLieder(enteredOldGroupLieder, studentManager.getStudent(enteredNewGroupLieder));
        }

    static void taskDone(){

        Scanner student = new Scanner(System.in);
        System.out.println("Введіть номер студента, якому хочете зарахувати ДЗ");
        int studentId = student.nextInt();
        tasksManager.addTask(studentId, "Завдання виконано" +  tasksManager.getTask(studentId));
    }


    static void createGroup(){

        displayStudents();
        int id = getUniqueData();

        Scanner studentLiederId = new Scanner(System.in);
        System.out.println("Введіть ID студента, яка буде старостою");
        int enteredId = studentLiederId.nextInt();//СТАРОСТА
        studentLiederManager.addStudentLieder(id, studentManager.getStudent(enteredId));
        studentManager.removeStudent(enteredId);

        Scanner studentId = new Scanner(System.in);
        System.out.println("Введіть через кому ID студентів, яких хочете додати в групу");
        String enteredStudentIds = studentId.nextLine();
        String[] numbers = enteredStudentIds.split(",");
        ArrayList<Integer> studentIdsList = new ArrayList<>();//МАСИВ СТУДЕНТІВ ДЛЯ ГРУПИ ТА ЗАВДАНЬ

        for(String number:numbers){
            int parserNumbers = Integer.parseInt(number.trim());
            studentIdsList.add(parserNumbers);
        }

        for(int key: studentIdsList){
           Student value = studentManager.getStudent(key);
            groupManager.addStudentToGroup(id, value);
        }

        Scanner scannerTask = new Scanner(System.in);
        System.out.println("Введіть завдання для створеної групи: ");
        String enteredStudentTask = scannerTask.nextLine();
        tasksManager.addTask(id, enteredStudentTask);//!!!!!!!!!
        for(int key: studentIdsList){
           tasksManager.addTask(key,enteredStudentTask);
        }

        System.out.println("Номер групи******************Староста*************Список студентів");
        groupDetails(id);


    }

    static void showGroupDetails(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть номер групи");
        int groupNumber = scanner.nextInt();
        groupDetails(groupNumber);
    }

    static void groupDetails(int groupId) {
        Group groupObj = groupManager.getGroup(groupId);
        if (groupObj != null) {
            System.out.println("Номер групи: " + groupObj.getGroupId());
            System.out.println("Староста: " + studentLiederManager.getStudentLieder(groupId).getStudentFirstName() + " " + studentLiederManager.getStudentLieder(groupId).getStudentLastName());
            System.out.println("Список студентів: ");
            List<Student> students = groupObj.getStudentGroup();

            for (Student student : students) {
                System.out.println(student.getStudentId() + " " + student.getStudentFirstName() + " " + student.getStudentLastName());
            }

            System.out.println("Завдання для студентів:");
            System.out.println("Студент: " + studentLiederManager.getStudentLieder(groupId).getStudentFirstName() + " " + studentLiederManager.getStudentLieder(groupId).getStudentLastName());
            System.out.println("Завдання: " + tasksManager.getTask(groupId));
            System.out.println("*");

            for (Student student : students) {
                System.out.println("Студент: " + student.getStudentFirstName() + " " + student.getStudentLastName());
                System.out.println("Завдання: " + tasksManager.getTask(student.getStudentId()));
                System.out.println();
            }
        } else {
            System.out.println("Групу не знайдено.");
        }
    }

    static void deleteStudent(){
        displayStudents();
        Scanner studentId = new Scanner(System.in);
        System.out.print("Введіть ID студента якого хочете видалити:");
        int enteredId = studentId.nextInt();
        studentManager.removeStudent(enteredId);
        displayStudents();
        System.out.println("Студента видалено!");
    }

    static void editStudentName(){
        displayStudents();

        Scanner studentId = new Scanner(System.in);
        Scanner studentFirstName = new Scanner(System.in);
        Scanner studentLastName = new Scanner(System.in);

        System.out.print("Введіть ID студента, у якого хочете редагувати Ім'я та Прізвище:");
        Integer enteredId = studentId.nextInt();

        System.out.println("Введіть прізвище студента");
        String enteredFirstName = studentFirstName.nextLine();
        System.out.println("Введіть імя студента");
        String enteredLAstName = studentLastName.nextLine();

        Student student = new Student(enteredId,enteredFirstName,enteredLAstName);
        studentManager.addStudent(student);
        displayStudents();
    }
}
