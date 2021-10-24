import java.util.Scanner;

public class Account{
    //Class Variables
    int balance;
    int previousTransaction;
    String customerName;
    String customerID;

    //Class constructer
    Account(String cname, String cid){
        customerName = cname;
        customerID = cid;
    }

    //Function for depositing money
    void deposit(int amount){
        if(amount != 0){
            balance = balance + amount;
            previousTransaction = amount;
        }
    }

    //Function for withdrawing money
    void withdraw(int amount){
        if(amount != 0){
            balance = balance - amount;
            previousTransaction = -amount;
        }
    }
    //Function showing previous transaction
    void getPreviousTransaction(){

        if(previousTransaction > 0){
            System.out.println("Deposited: " + previousTransaction);
        } else if(previousTransaction < 0){
            System.out.println("Withdrawn: " + Math.abs(previousTransaction));
        } else { System.out.println("No transaction occured");}
    }
    //Function calculating the interest after a certain amount of years with the current balance
    void calculateInterest(int years){

        double interestRate = .0185;
        double newBalance = (balance * interestRate * years) + balance;
        System.out.println("The current interest rate is " + (100 * interestRate));
        System.out.println("After " + years + " years, you bbalance will be: " + newBalance);
    }

    //Function showing main menu
    void showMenu(){

        char option = '\0';
        Scanner input = new Scanner(System.in);
        System.out.println("\nWelcome, " + customerName + "!");
        System.out.println("Your ID is: " + customerID);
        System.out.println("\nWhat would you like to do?");
        System.out.println("\nA. Check your balance");
        System.out.println("B. Make a deposit");
        System.out.println("C. Make a withdrawal");
        System.out.println("D. View previous transacttion");
        System.out.println("E. Calculate interest");
        System.out.println("F. Exit");

        do{

            
            System.out.println("\nEnter");
            char option1 = input.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();
            

            switch(option) {
                //Case "A" For checking balance amount
                case 'A':
                    System.out.println("============");
                    System.out.println("Balance = $" + balance);
                    System.out.println("============");
                    System.out.println();
                    break;
                //Case "B" to make a deposit    
                case 'B':
                    System.out.println("Eneter an amount to depspot: ");
                    int amount = input.nextInt();
                    deposit(amount);
                    System.out.println();
                    break;
                //Case "C" to withdraw money    
                case 'C':
                    System.out.println("Enter amount to withdraw: ");
                    int amount2 = input.nextInt();
                    withdraw(amount2);
                    System.out.println();
                    break;
                //Case "D" to view most recent transaction
                case 'D':
                    System.out.println("===========");
                    getPreviousTransaction();
                    System.out.println("===========\n");
                    
                break;
                //Case "E" to calculate interest with current amount based on said specified year
                case 'E':
                    System.out.println("Enter how many years of accured interest: ");
                    int years = input.nextInt();
                calculateInterest(years);
                break;
                //Case "F" to exit
                case 'F':
                    System.out.println("===========");
                    break;
                default:
                System.out.println("Error: invalid option. Please enter A, B, C, D, E, or F");
            }
        } while(option != 'F');
            System.out.println("Thank you for banking with us!");
    }
}