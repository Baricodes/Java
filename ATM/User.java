import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    
    private String firstName; // First name of user
    
    private String lastName; // Last name of user
   
    private String uuid; // The ID of the user
   
    private byte[] pinHash; // The MD5 hash of the user's pin number.

    private ArrayList<Account> accounts; // The list of accounts for this user

    /**
     * Create a new user
     * @param firstName the user's firstName
     * @param lastName the user's lastName
     * @param pin the user's pin
     * @param theBank the Bank object that tge user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank){

        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // strore the pin's MD5 hash, rather than the original value
        // Its in a try catch block to handle the exception thrown
       try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
            // Our message digest object md, calls the method digest which use the MD5 
            // Algotirhtm on our pin which is getting the bytes of the memory address
            // our pin is stored at. Then it is storing that inside our pinHashh which is a array of bytes
            this.pinHash = md.digest(pin.getBytes());

       } catch (NoSuchAlgorithmException e) {
            
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
       }

       // get a new, unique universal ID for the user
       this.uuid = theBank.getNewUserUUID();

       // create empty list of accounts
       this.accounts = new ArrayList<Account>();

       //print log message
       System.out.printf("New user %s %s with ID %s created.\n", lastName, firstName, this.uuid);
        

    }

    /**
     * Add an account for the user
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    /**
     *  returns the UUID
     * @return the uuid
     */
    public String getUUID(){

        return this.uuid;
    }

    /**
     * Check whether a given pin mathces the true User pin
     * @param aPin the pin to checl
     * @return whether the pin is valid or not
     */
    public boolean validatePin(String aPin){

        try {
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
    
        } catch (NoSuchAlgorithmException e) {
             
             System.err.println("error, caught NoSuchAlgorithmException");
             e.printStackTrace();
             System.exit(1);
        }

        return false;
    }

    /**
     * Return the user's first name.
     * @return the first name
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Print summaries for the accounts of this user
     */
    public void printAccountsSummary(){
        
        System.out.printf("%s's accounts summary\n", this.firstName);
        for ( int a = 0; a < this.accounts.size(); a++){
            System.out.printf("  %d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     * @return the number of accounts
     */
    public int numAccouts(){
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account
     * @param acctIndx the index of the account to use
     */
    public void printAcctTransHistory(int acctIndx){
        this.accounts.get(acctIndx).printTransHistory();
    }

    /**
     * Get the balance of a particular account
     * @param acctIndx the index of the account to use
     * @return the balance of the account
     */
    public double getAcctBalance(int acctIndx){
        return this.accounts.get(acctIndx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param acctIndx the index of the account to use
     * @return the UUID of the account
     */
    public String getAcctUUID(int acctIndx){
        return this.accounts.get(acctIndx).getUUID();
    }

    public void addAcctTransaction(int acctIndx, double amount, String memo){
        this.accounts.get(acctIndx).addTransaction(amount, memo);
    }

}
