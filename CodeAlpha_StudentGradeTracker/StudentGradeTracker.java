import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeTracker{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input student details
        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter marks: ");
            int marks = sc.nextInt();
            sc.nextLine();

            students.add(new Student(name, marks));
        }

        // Calculations
        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (Student s : students) {
            total += s.marks;
            if (s.marks > highest)
                highest = s.marks;
            if (s.marks < lowest)
                lowest = s.marks;
        }

        double average = (double) total / n;

        // Summary Report
        System.out.println("\n========== STUDENT SUMMARY REPORT ==========");
        System.out.println("Name\t\tMarks");
        System.out.println("-------------------------------------------");

        for (Student s : students) {
            System.out.println(s.name + "\t\t" + s.marks);
        }

        System.out.println("-------------------------------------------");
        System.out.println("Average Marks : " + average);
        System.out.println("Highest Marks : " + highest);
        System.out.println("Lowest Marks  : " + lowest);

        sc.close();
    }
}
