import java.util.Scanner;

class Grade {
    public String calculateGrade(double percentage) {
        if (percentage >= 85) {
            return "A+";
        } else if (percentage >= 75) {
            return "A";
        } else if (percentage >= 70) {
            return "B+";
        } else if (percentage >= 65) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Grade g = new Grade();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the amount of subjects");
        int tsubs = sc.nextInt();
        int tmarks = 0;

        for (int i = 0; i < tsubs; i++) {
            System.out.println("Enter the marks obtained in subject " + (i + 1));
            int marks = sc.nextInt();
            tmarks += marks;
        }

        double percentage = (double) tmarks / tsubs;
        String grade = g.calculateGrade(percentage);

        System.out.println("Total marks obtained: " + tmarks);
        System.out.println("Percentage obtained: " + percentage + "%");
        System.out.println("Grade obtained: " + grade);

        sc.close();
    }
}
