package entity;

import service.GradeCalculator;

import java.util.Map;

public class Student extends Person implements Reportable {

    private int rollNumber;
    private Map<Subject,Double> marks;
    private static int studentsCount;

    static{
        studentsCount = 0;
    }

    public Student(){
        super();
        studentsCount++;
    }

    public Student(int id, String name, int rollNumber, Map<Subject, Double> marks) {
        super(id, name);
        this.rollNumber = rollNumber;
        this.marks = marks;
        studentsCount++;
    }


    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setMarks(Map<Subject, Double> marks) {
        this.marks = marks;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public Map<Subject, Double> getMarks() {
        return marks;
    }

    public static int getStudentsCount() {
        return studentsCount;
    }

    
    public double getTotalMarks(){

        double totalMarks = 0.0;

        for (double mark : marks.values()){
            totalMarks += mark;
        }

        return  totalMarks;
    }

    public double getAverageMarks(){
        double totalMarks = getTotalMarks();
        int countSubjects = marks.size();

        return (totalMarks/countSubjects);
    }

    public boolean addMark(Subject subject, double mark){
        if (mark < 0 || mark > subject.getMaxMarks()){
            System.out.println("This marks is invalid.");
            return false;
        }
        marks.put(subject,mark);
        System.out.println("Mark added successfully");
        return true;
    }

    public boolean updateMark(Subject subject, double mark){

        if(marks.containsKey(subject)){
            if (mark < 0 || mark > subject.getMaxMarks()){
                System.out.println("This marks is invalid.");
                return false;
            }
            marks.replace(subject, marks.get(subject) , mark);
            System.out.println("Mark updated successfully");
            return true;
        }
        System.out.println("Subject not found");
        return false;

    }

    @Override
    public String generateReport(){

        StringBuilder stringBuilder = new StringBuilder("===== Student's Report Card =====\n");

        stringBuilder.append("Name: " + getName() + "\n"
                + "Roll number: " + getRollNumber() + "\n"
                + "-- Marks per subject --\n");


        for ( Map.Entry<Subject,Double> mark : marks.entrySet() ){
            stringBuilder.append(mark.getKey().toString() + " : " + mark.getValue() + "\n");
        }

        stringBuilder.append("-----------------------\n");

        stringBuilder.append("Total Marks: " + getTotalMarks() + "\n");

        stringBuilder.append("Average Marks: " + getAverageMarks() + "\n");

        double totalMarksOfSubjects = 0.0;
        for (Subject mark : marks.keySet()){
            totalMarksOfSubjects += mark.getMaxMarks();
        }

        double percentage = GradeCalculator.calculatePercentage(getTotalMarks(),totalMarksOfSubjects);

        stringBuilder.append("Grade: " + GradeCalculator.calculateGrade(percentage) + "\n");

        switch (GradeCalculator.calculateGrade(percentage)){
            case 'F':
                stringBuilder.append("Fail\n");
                break;
            default:
                stringBuilder.append("Pass\n");
                break;
        }

        return stringBuilder.toString();
    }
}
