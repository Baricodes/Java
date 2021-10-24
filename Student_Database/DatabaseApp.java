import java.util.Scanner;

public class DatabaseApp {
    
    public static void main(String[] args) {

        Student stu1 = new Student();
        stu1.enroll();
        stu1.payTuition(28);
        stu1.showInfo();

        System.out.println("Enter number of new students to enroll");
        Scanner input = new Scanner(System.in);
        int numOfStudents = input.nextInt();
        Student[] students = new Student[numOfStudents];

        // Create number of new students
        for (int n = 0; n < numOfStudents; n++) {
            students[n] = new Student();
            students[n].enroll();
            students.payTuition();
        }

        for (int n = 0; n < numOfStudents; n++){

            System.out.println(students[n].showInfo());
        }
    }
}