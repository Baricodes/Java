import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {

        List<Teacher> teacherList = new ArrayList<>();

        Teacher lizzy = new Teacher(1,"Lizzy",500);
        Teacher melissa = new Teacher(2,"Mellisa",700);

        teacherList.add(lizzy);
        teacherList.add(melissa);

        List<Student> studentList = new ArrayList<>();
        
        Student tara = new Student(1,"Tara",4);
        Student larry = new Student(2,"Larry",12);

        studentList.add(tara);
        studentList.add(larry);

        School kane = new School(teacherList,studentList);
        
        tara.payFees(5000);
        larry.payFees(900);
        
        System.out.println("Kane has earned: $" + kane.getTotalMoneyEarned());

        System.out.println("School paying salary of:");

        lizzy.receiveSalary(lizzy.getSalary());
        System.out.println("For: " + lizzy.getName() + " and now has " + kane.getTotalMoneyEarned());

        System.out.println(tara);
        System.out.println(lizzy);

    }
}
