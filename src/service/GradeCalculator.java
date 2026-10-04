package service;

public class GradeCalculator {

    public static char calculateGrade(double percentage){
        if (percentage >= 0.9){
            return 'A';
        }
        else if (percentage >= 0.8){
            return 'B';
        }
        else if (percentage >= 0.7){
            return 'C';
        }
        else if (percentage >= 0.5){
            return 'D';
        }
        else {
            return 'F';
        }
    }

    public static double calculatePercentage(double obtained, double max){
        return (obtained/max);
    }
}
