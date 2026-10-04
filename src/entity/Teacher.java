package entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person implements Reportable {

    private String subjectSpecialization;
    private int employeeId;
    private List<Student> studentsTaught;

    public Teacher(int id, String name, String subjectSpecialization, int employeeId, List<Student> studentsTaught) {
        super(id, name);
        this.subjectSpecialization = subjectSpecialization;
        this.employeeId = employeeId;
        this.studentsTaught = studentsTaught;
    }

    public void setStudentsTaught(List<Student> studentsTaught) {
        this.studentsTaught = studentsTaught;
    }

    public void setSubjectSpecialization(String subjectSpecialization) {
        this.subjectSpecialization = subjectSpecialization;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getSubjectSpecialization() {
        return subjectSpecialization;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public List<Student> getStudentsTaught() {
        return studentsTaught;
    }

    public List<Student> viewStudents(){
        return studentsTaught;
    }

    public boolean addMark(Subject subject, double mark, Student student){
        return student.addMark(subject,mark);
    }

    public boolean updateMark(Subject subject, double mark, Student student){
        return student.updateMark(subject,mark);
    }

    @Override
    public String generateReport(){

        StringBuilder stringBuilder = new StringBuilder("===== Teacher's report =====\n");
        stringBuilder.append(
                "Name: " + getName() +"\n"+
                        "Id: " + getEmployeeId()+"\n"
                        + "Specialization: " + getSubjectSpecialization());

        return stringBuilder.toString();
    }
}
