import java.util.Scanner;

public class Email {

    private String firstName;
    private String lastName;
    private String password;
    private int defaultPassLength = 10;
    private String email;
    private String department;
    private int mailboxCapacity;
    private String alternateEmail;
    private String companySuffix = "onepiece.com";

    //Email constructer
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        
        // Calling method asking for department
        this.department = setDepartment();

        // Calling method asking to set a new password
        this.password = randomPassword(defaultPassLength);

        // Combining email elements
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + "." + companySuffix;
    }

    //Function asking for setting which department // #TODO: use while loop to maintain display of menu options. ADD a exit option.
    private String setDepartment(){

        System.out.println("Enter your department:\n1. for Sales\n2. for Development\n3. for Accounting");
        Scanner input = new Scanner(System.in);
        int depChoice = input.nextInt();

        if (depChoice == 1) {return "sales";}
        else if (depChoice == 2) { return "dev";}
        else if (depChoice == 3) {return "acct";}
        else {return " ";}
    }

    //Generating a random password
    private String randomPassword(int length){

        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";
        char[] password = new char[length];
        for (int i=0; i<length; i++) {
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    public void showInfo() {
        
        System.out.printf("User Name: %s %s\n",firstName,lastName);
        System.out.printf("Your password is: %s\n",this.password);
        System.out.printf("Company Email: %s\n",email);
        System.out.printf("Mailbbox Capacity: %d mb\n",mailboxCapacity);
        
    }

    // Setter for the mailbox capacity
    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    // Setter for the alternate email  // #TODO: use Math.random to generate an alternate email address
    private void setAlternateEmail(String email){
        this.alternateEmail = email;
    }
    
    // Setter to change the password  // #TODO: verify current password to change password
    private void changePassword(String password){
        this.password = password;
    }

    public int getMailboxCapacity(){
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public String getPassword() {
        return password;
    }
}