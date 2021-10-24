import java.util.Scanner;

public class ATM {
    
    public static void main(String[] args) {
        
        // intialize scanner
        Scanner input = new Scanner(System.in);

        // intialize bank
        Bank theBank = new Bank("Bank of Duran & Duransanty");

        // add a user, which aslo creates a savings account
        User aUser = theBank.addUser("Joe", "LaBeaux", "2008");

        // add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while(true) {

            //stay in the login prompt until succesfull login
            curUser = ATM.mainMenuPrompt(theBank, input);

            // stay in main menu until user quits
            ATM.printUserMenu(curUser, input);
        }
    } 
    
    /**
     *  ATM's login menu
     * @param theBank the Bank object whose accounts to use
     * @param input the Scanner object to use for user input
     * @return the authenticated user
     */
    public static User mainMenuPrompt(Bank theBank, Scanner input){

        //inits
        String userID;
        String pin;
        User authUser;

        // prompt the user for user ID/pin combo until a correct one is reached to login
        do{

            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = input.nextLine();
            System.out.print("Enter pin: ");
            pin = input.nextLine();

            // try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/pin combonation. " + "Please try again. ");
            }

        } while(authUser == null); // continue looping until succeful login

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner input) {

        // print a summary of the user's accounts
        theUser.printAccountsSummary();

        // init
        int choice;

        // User menu
        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println("  1) Show account transaction history");
            System.out.println("  2) Withdrawl");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Logout\n");
            System.out.print("Enter chioce: ");
            choice = input.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5");
            }
            
        } while(choice < 1 || choice > 5);

        // process the choice
        switch(choice){

            case 1: 
                ATM.showTransHistory(theUser, input);
                break;
            case 2:
                ATM.withdrawFunds(theUser, input);
                break;
            case 3: 
                ATM.depositFunds(theUser, input);
                break;
            case 4:
                ATM.transferFunds(theUser, input);
                break;
            case 5:
                input.nextLine(); // gobble up the rest of the previous input line
                break;
        }

        // redisplay menu unless user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(theUser, input);
        }
    }

    /**
     * Show the transaction history for an account
     * @param theUser the looged-in User object
     * @param input the Scanner object used for user input
     */
    public static void showTransHistory(User theUser, Scanner input){

        int theAcct;

        // get account whose transaction history to look at
        do {

            System.out.printf("Enter the number (1-%d) of the account whose history you'd like to view:\n", theUser.numAccouts());

            theAcct = input.nextInt() - 1;
            if (theAcct < 0 || theAcct >= theUser.numAccouts()){
                System.out.println("Invalid account. Please try again");
            }
        } while(theAcct < 0 || theAcct >= theUser.numAccouts());

        // print the transaction history
        theUser.printAcctTransHistory(theAcct);
    } 

    /**
     * transferring funds from one account to another
     * @param theUser the logged-in User object
     * @param input the Scanner object used for input
     */
    public static void transferFunds(User theUser, Scanner input){

        // inits
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer from.\n", theUser.numAccouts());
            fromAcct = input.nextInt() -1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccouts()){
                System.out.println("Invalid account. PLease try again,");
            }

        } while(fromAcct < 0 || fromAcct >= theUser.numAccouts());

        acctBal = theUser.getAcctBalance(fromAcct);

        //get account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer to.\n", theUser.numAccouts());
            toAcct = input.nextInt() -1;
            if (toAcct < 0 || toAcct >= theUser.numAccouts()){
                System.out.println("Invalid account. Please try again,");
            }

        } while(toAcct < 0 || toAcct >= theUser.numAccouts());

        // get the amount to transfer
        do{
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = input.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal){
                System.out.printf("Amount must not be greater than\n" +
                 "balance of $%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        // Finally do the transfer
        theUser.addAcctTransaction(fromAcct, -1*amount, String.format("transfer to account %s",
        theUser.getAcctUUID(toAcct)));
        theUser.addAcctTransaction(toAcct, amount, String.format("transfer from account %s",
        theUser.getAcctUUID(fromAcct)));
    }

    /**
     * Process a fund withdraw from an account
     * @param theUser the logged-in User object
     * @param input the Scnner object user for user input
     */
    public static void withdrawFunds(User theUser, Scanner input){

         // inits
         int fromAcct;
         double amount;
         double acctBal;
         String memo;
 
         // get the account to withdraw from
         do {
             System.out.printf("Enter the number (1-%d) of the account to withdraw from\n", theUser.numAccouts());
             fromAcct = input.nextInt() -1;
             if (fromAcct < 0 || fromAcct >= theUser.numAccouts()){
                 System.out.println("Invalid account. PLease try again,");
             }
 
         } while(fromAcct < 0 || fromAcct >= theUser.numAccouts());
         acctBal = theUser.getAcctBalance(fromAcct);

         // get the amount to withdraw
         do{
            System.out.printf("Enter the amount to withdraw (max $%.02f): $", acctBal);
            amount = input.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal){
                System.out.printf("Amount must not be greater than\n" +
                 "balance of $%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        // gobble up the rest of the previous input line
        input.nextLine();

        // get a memo
        System.out.println("Enter a memo: ");
        memo = input.nextLine();

        // do the withdrawl
        theUser.addAcctTransaction(fromAcct, -1*amount, memo);
    }

    /**
     * process a fund deposit to an account
     * @param theUser the logged-in User object
     * @param input the Scanner object used for user input
     */
    public static void depositFunds(User theUser, Scanner input){

         // inits
         int toAcct;
         double amount;
         double acctBal;
         String memo;
 
         // get the account to deposit to
         do {
             System.out.printf("Enter the number (1-%d) of the account to deposit into\n", theUser.numAccouts());
             toAcct = input.nextInt() -1;
             if (toAcct < 0 || toAcct >= theUser.numAccouts()){
                 System.out.println("Invalid account. PLease try again,");
             } 
 
         } while(toAcct < 0 || toAcct >= theUser.numAccouts());
         acctBal = theUser.getAcctBalance(toAcct);

         // get the amount to deposit
         do{
            System.out.printf("Enter the amount to deposit (max $%.02f): $", acctBal);
            amount = input.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } 
        } while (amount < 0);

        // gobble up the rest of the previous input line
        input.nextLine();

        // get a memo
        System.out.println("Enter a memo: ");
        memo = input.nextLine();

        // do the withdrawl
        theUser.addAcctTransaction(toAcct, amount, memo);
        

    }
}
