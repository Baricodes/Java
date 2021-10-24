import java.util.ArrayList;

public class Account {

    private String name; // name for what kind of account
    
    private String uuid; // The accounts ID number
    
    private User holder; // The User object that holds this account

    private ArrayList<Transaction> transactions; // The list of transactions for this account

    /**
     * Create a new Account
     * @param name name of account
     * @param holder the User object that holds of the account
     * @param theBank the bank that issues the account
     */
    public Account(String name, User holder, Bank theBank){

        // set the account name and holder
        this.name = name;
        this.holder = holder;

        // get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        //initialize transactions
        this.transactions = new ArrayList<Transaction>();

    }

    /**
     * returns the account uuid
     * @return the uuid
     */
    public String getUUID(){
        return this.uuid;
    }

    /**
     *  Get summary line for the account
     * @return the String summary
     */
    public String getSummaryLine(){

        // get the account's balance
        double balance = this.getBalance();

        // fromat the summary line, depending on whether the balance is 
        // negative
        if (balance >= 0){
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f : %s", this.uuid, balance, this.name);
        }
    }

    /**
     * Get's the balance this account by adding all of the transactions
     * associated with it
     * @return a double representing the account's balance
     */
    public double getBalance(){
        
        double balance = 0;
        for (Transaction t : this.transactions){
            balance += t.getAmount();
        }
        
        return balance;
    }
    
    /**
     * Print the transaction history of the account
     */
    public void printTransHistory(){

        System.out.printf("\n\tTransaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size() - 1; t >= 0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add a new transaction in this account
     * @param amount the amount of the transaction
     * @param memo the memo of the transaction
     */
    public void addTransaction(double amount, String memo){

        // create a new transaction object and add it to our list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }

}
