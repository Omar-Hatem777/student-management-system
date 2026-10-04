package app;

import entity.*;
import java.util.*;

public class Main {

    //region Helper methods
    private static int validateIntInput(Scanner sc){
        while (!sc.hasNextInt()){
            System.out.println("Input must be an integer.");
            sc.next();
        }
        return sc.nextInt();
    }

    private static Double validateDouble(Scanner sc){
        while(!sc.hasNextDouble()){
            System.out.println("Input must be a number.");
            sc.next();
        }
        return sc.nextDouble();
    }

    private static void printNamesList(List<? extends Person> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) +" "+ list.get(i).getName());
        }
    }

    private static void printSubjectList(List<Subject> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) +" "+ list.get(i).getSubjectName());
        }
    }

    private static Student createStudent(int studentId, String studentName, int rollNumber, Map<Subject,Double> studentGrades){
        return new Student(studentId,studentName,rollNumber,studentGrades);
    }

    private static Teacher createTeacher(int teacherId,String teacherName, String subjectSpecialization, int employeeId, List<Student> studentsTaught){
        return new Teacher(teacherId,teacherName,subjectSpecialization,employeeId,studentsTaught);
    }

    private static void addMark(Teacher teacher, Subject subject, double mark, Student student){
        if (teacher.addMark(subject,mark,student))
        {
            System.out.println("Mark added successfully.");
        }
        else
        {
            System.out.println("Error");
        }
    }

    private static void updateMark(Teacher teacher, Subject subject, double mark, Student student){
        if (teacher.updateMark(subject,mark,student))
        {
            System.out.println("Mark updated successfully.");
        }
        else
        {
            System.out.println("Error");
        }
    }

    private static void showStudentReport(List<Student> students, int studentIndex){
        System.out.println(students.get(studentIndex).generateReport());
    }

    private static void showTeacherReport(List<Teacher> teachers, int teacherIndex){
        System.out.println(teachers.get(teacherIndex).generateReport());
    }

    private static void showAllReports(List<Teacher> teachers, List<Student> students){
        List<Reportable> reportables = new ArrayList<>();
        reportables.addAll(teachers);
        reportables.addAll(students);
        for (Reportable reportable : reportables){
            System.out.println(reportable.generateReport());
        }
    }
    //endregion


    public static void main(String[] args) {

        //region Subjects
        Subject english = new Subject("English", 100);
        Subject arabic = new Subject("Arabic", 150);
        Subject french = new Subject("French", 90);
        Subject math = new Subject("Math", 200);
        Subject science = new Subject("Science", 110);

        List<Subject> subjects = new ArrayList<>();
        subjects.add(english);
        subjects.add(arabic);
        subjects.add(french);
        subjects.add(math);
        subjects.add(science);
        //endregion

        //region Initial students creation
        Student omar = new Student();
        omar.setId(1);
        omar.setName("Omar");
        omar.setRollNumber(20226070);
        Map<Subject,Double> omarMarks = new HashMap<>();
        omarMarks.put(english,95.0);
        omarMarks.put(arabic,110.0);
        omarMarks.put(french,80.0);
        omar.setMarks(omarMarks);

        Map<Subject,Double> ahmedMarks = new HashMap<>();
        ahmedMarks.put(english,95.0);
        ahmedMarks.put(arabic,150.0);
        ahmedMarks.put(french,90.0);
        Student ahmed = new Student(2,"Ahmed",20226071,ahmedMarks);
        List<Student> students = new ArrayList<>();
        students.add(omar);
        students.add(ahmed);
        //endregion

        //region Initial teacher creation
        Teacher teacher = new Teacher(3,"Rezk","english",201,students);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        //endregion

        //region Variables
        String listErrorMessage = "You did not choose any of the previous list";
        String teacherErrorMessage = "There are not teachers, please add a teacher first.";
        Scanner scanner = new Scanner(System.in);
        int closeMain = 1;
        //endregion

        while (closeMain == 1){

            //region Initial program prompts
            System.out.println("Please choose one of the following options: ");
            System.out.println("0: Close application.");
            System.out.println("1: Create a student.");
            System.out.println("2: Create a teacher.");
            System.out.println("3: Add a mark (via a teacher).");
            System.out.println("4: Update a mark (via a teacher).");
            System.out.println("5: View a student's report.");
            System.out.println("6: View a teacher's report.");
            System.out.println("7: View all reports");
            //endregion

            int x = validateIntInput(scanner);

            switch (x){

                //region Case 0
                case 0:
                    closeMain = 0;
                    System.out.println("Bye bye......");
                    break;
                //endregion

                //region Case 1
                case 1:

                    System.out.println("Please enter user Id: ");
                    int studentId = validateIntInput(scanner);

                    System.out.println("Please enter student name: ");
                    String studentName = scanner.next();

                    System.out.println("Please enter student roll number: ");
                    int rollNumber = validateIntInput(scanner);

                    Map<Subject,Double> studentGrades = new HashMap<>();
                    for (Subject subject  : subjects) {
                        System.out.println("Please enter the " + subject.getSubjectName() +" grade: ");
                        double grade = validateDouble(scanner);
                        studentGrades.put(subject,grade);
                    }

                    students.add(createStudent(studentId,studentName,rollNumber,studentGrades));
                    System.out.println("Student " + studentName + " created successfully");

                    break;
                //endregion

                //region Case 2
                case 2:
                    System.out.println("Please enter user Id: ");
                    int teacherId = validateIntInput(scanner);


                    System.out.println("Please enter teacher name: ");
                    String teacherName = scanner.next();

                    System.out.println("Please enter subject specialization: ");
                    String subjectSpecialization = scanner.next();

                    System.out.println("Please enter student employee id: ");
                    int employeeId = validateIntInput(scanner);


                    System.out.println("Please select the students you taught(choose numbers separated by commas): ");
                    printNamesList(students);

                    String studentsTaughtIndexes = scanner.next();

                    List<Student> studentsTaught = new ArrayList<>();
                    String[] tokens = studentsTaughtIndexes.split(",");

                    for (String token : tokens) {
                        try {
                            int index = Integer.parseInt(token.trim()) - 1;
                            if (index < 0 || index >= students.size()){
                                System.out.println("The student number " + (index + 1) + " doesn't exist");
                                continue;
                            }
                            studentsTaught.add(students.get(index));
                        } catch (NumberFormatException e) {
                            System.out.println("'" + token + "' is not a valid number, skipping.");
                        }
                    }

                    teachers.add(createTeacher(teacherId,teacherName,subjectSpecialization,employeeId,studentsTaught));
                    System.out.println("Teacher created successfully");

                    break;
                //endregion

                //region Case 3
                case 3:

                    if (teachers.isEmpty()){
                        System.out.println(teacherErrorMessage);
                        break;
                    }

                    System.out.println("Please select a teacher: ");
                    printNamesList(teachers);

                    int teacherIndex = (validateIntInput(scanner)) - 1;
                    if (teacherIndex < 0 || teacherIndex >= teachers.size()){
                        System.out.println(listErrorMessage);
                        break;
                    }
                    Teacher t = teachers.get(teacherIndex);

                    System.out.println("Please select subject: ");
                    printSubjectList(subjects);
                    int subjectIndex = (validateIntInput(scanner)) - 1 ;

                    if (subjectIndex < 0 || subjectIndex >= subjects.size()){
                        System.out.println(listErrorMessage);
                        break;
                    }
                    Subject s = subjects.get(subjectIndex);

                    System.out.println("Please select student to add mark: ");
                    printNamesList(t.getStudentsTaught());
                    int studentIndex = (validateIntInput(scanner)) - 1 ;

                    if (studentIndex < 0 || studentIndex >= t.getStudentsTaught().size()){
                        System.out.println(listErrorMessage);
                        break;
                    }
                    Student ss = t.getStudentsTaught().get(studentIndex);

                    System.out.println("Please enter the mark: ");
                    double mark = validateDouble(scanner);

                    addMark(t,s,mark,ss);

                    break;
                //endregion

                //region Case 4
                case 4:

                    if (teachers.isEmpty()){
                        System.out.println(teacherErrorMessage);
                        break;
                    }

                    System.out.println("Please select a teacher: ");
                    printNamesList(teachers);
                    int teacherIndex1 = (validateIntInput(scanner)) - 1 ;

                    if (teacherIndex1 < 0 || teacherIndex1 >= teachers.size()){
                        System.out.println(listErrorMessage);
                        break;
                    }
                    Teacher t1 = teachers.get(teacherIndex1);

                    System.out.println("Please select subject: ");
                    printSubjectList(subjects);
                    int subjectIndex1 = (validateIntInput(scanner)) - 1 ;

                    if (subjectIndex1 < 0 || subjectIndex1 >= subjects.size()){
                        System.out.println(listErrorMessage);
                        break;
                    }
                    Subject s1 = subjects.get(subjectIndex1);

                    System.out.println("Please select student to update mark: ");
                    printNamesList(t1.getStudentsTaught());
                    int studentIndex1 = (validateIntInput(scanner)) - 1 ;

                    if (studentIndex1 < 0 || studentIndex1 >= t1.getStudentsTaught().size()){
                        System.out.println(listErrorMessage);
                        break;
                    }
                    Student ss1 = t1.getStudentsTaught().get(studentIndex1);

                    System.out.println("Please enter the mark: ");
                    double mark1 = validateDouble(scanner);

                    updateMark(t1,s1,mark1,ss1);

                    break;
                //endregion

                //region Case 5
                case 5:

                    if (students.isEmpty()){
                        System.out.println("There are no students, please add one.");
                        break;
                    }

                    System.out.println("Please choose a student: ");
                    printNamesList(students);
                    int studentIndex2 = (validateIntInput(scanner)) - 1;

                    if (studentIndex2 < 0 || studentIndex2 >= students.size()){
                        System.out.println(listErrorMessage);
                        break;
                    }

                    showStudentReport(students,studentIndex2);

                    break;
                //endregion\\

                //region case 6
                case 6:

                    if (teachers.isEmpty()){
                        System.out.println(teacherErrorMessage);
                        break;
                    }

                    System.out.println("Please choose a teacher: ");
                    printNamesList(teachers);
                    int teacherIndex2 = (validateIntInput(scanner)) - 1;

                    if (teacherIndex2 < 0 || teacherIndex2 >= teachers.size()){
                        System.out.println(listErrorMessage);
                        break;
                    }

                    showTeacherReport(teachers,teacherIndex2);

                    break;
                //endregion

                //region Case 7
                case 7:

                    showAllReports(teachers,students);

                    break;
                //endregion

                //region Default
                default:
                    System.out.println("This choice is not available.");
                    break;
                //endregion

            }
        }
    }
}