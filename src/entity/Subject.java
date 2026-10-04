package entity;

public class Subject {

    private String subjectName;
    private double maxMarks ;

    public Subject(String subjectName, double maxMarks) {
        this.subjectName = subjectName;
        this.maxMarks = maxMarks;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setMaxMarks(double maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public double getMaxMarks() {
        return maxMarks;
    }

    @Override
    public String toString(){
        return getSubjectName();
    }
}
