import java.util.List;
import java.util.ArrayList;

public class School {

private List<Teacher> teachers;
private List<Student> students;
private static double totalMoneyEarned;
private static double totalMoneySpent;

/**
 * 
 * @param teachers
 * @param students
 */
public School(List<Teacher> teachers, List<Student> students){
    this.teachers = teachers;
    this.students = students;
    totalMoneyEarned = 0;
    totalMoneySpent = 0;
}

/**
 * 
 * @return List of Teachers in the school
 */
public List<Teacher> getTeachers(){
    return this.teachers;
}

/**
 * 
 * @return List of Students in the school
 */
public List<Student> getStudents(){
    return this.students;
}

/**
 * 
 * @return A double total money earned
 */
public double getTotalMoneyEarned(){
    return totalMoneyEarned;
}

/**
 * 
 * @return A double total money spent
 */
public double getTotalMoneySpent(){
    return totalMoneySpent;
}

/**
 * 
 * @param teacher teacher to be added to the list of teachers
 */
public void addTeacher(Teacher teacher){
    teachers.add(teacher);
}

/**
 * 
 * @param student student to be added to the list of students
 */
public void addStudent(Student student){
    students.add(student);
}

/**
 * 
 * @param totalMoneyEarned total amount of money earned
 */
public static void updateTotalMoneyEarned(double moneyEarned){
    totalMoneyEarned += moneyEarned;
}

/**
 * 
 * @param totalMoneySpent total amount of money spent
 */
public static void updateTotalMoneySpent(double moneySpent){
    totalMoneyEarned -= moneySpent;
}

}
