import java.util.Scanner;

public class Student {
    private String firstName;
    private String lastName;
    private int gradeYear;
    private String studentID;
    private String courses = null;
    private int tutitionBalance = 0;
    private static int costOfCourses = 400;
    private static int id = 1000;

    // Constructer prompt student to enter name and year
    public  Student() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter student first name: ");
        this.firstName = input.nextLine();

        System.out.print("Enter student last name: ");
        this.lastName = input.nextLine();

        System.out.print("1 - Freshmen\n2 - Sophmore\n3 - Junior\n4 - Senior\nEnter student class level: ");
        this.gradeYear = input.nextInt();
        
        setStudentID();

        System.out.printf("\nStudent ID: %s\n",studentID);

    }

    // Generate an ID
    private void setStudentID(){
        // Grade level + ID
        id++;
        this.studentID = gradeYear + "" + id; 
    }

    // Method for students to enroll
    public void enroll(){
        do{
            System.out.println("\nEnter course to enroll (0 to quit):");
            Scanner input = new Scanner(System.in);
            String course = input.nextLine();

            if (!course.equalsIgnoreCase("Q")){
                courses += "\n" + course;
                tutitionBalance += costOfCourses;
            } else {break;}

        } while (1!= 0);
    }

    // Method to view balance
    public void viewBalance(){
        System.out.println("\nYour balanec is: $" + tutitionBalance);
    }

    // Method to view balance
    public void payTuition(int payment){
        viewBalance();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your payment amount: $ ");
        input.nextInt();
        tutitionBalance -= payment;
        System.out.println("Thank you for your payment of " + payment);
        viewBalance();
    }

    // Method to view info
    public void showInfo(){
        System.out.printf("Name: %s %s",firstName,lastName);
        System.out.printf("\nStudentID: %s",studentID);
        System.out.printf("\n Enrolled: %s",courses);
        System.out.printf("\nBalance: $%d", tutitionBalance);
    }
}
