import java.until

public class Email_App {

    String firstName;
    String lastname;
     public static void main(String[] args) {
        
        System.out.println("Welcome to make a Email!");
       

        Email em1 = new Email("John", "Hancock");
        em1.showInfo();
    }
}
