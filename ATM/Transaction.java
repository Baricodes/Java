import java.util.Date;

public class Transaction {
    
    private double amount; // The amount of the transaction

    private Date timeStamp; // The time and date of this transaction

    private String memo; // A memo for this transaction
    
    private Account inAccount; // The account in which the transaction was preformed

    /**
     * Create a transaction
     * @param amount the amount of the transaction
     * @param inAccount the accout object the transaction belongs too
     */
    public Transaction(double amount, Account inAccount){

        this.amount = amount;
        this.inAccount = inAccount;
        this.timeStamp = new Date();
        this.memo = "";
         
    }

    /**
     * Transaction second constructor to include a memo
     * @param amount amount of the transaction
     * @param memo memo with the transaction
     * @param inAccount the account object the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount){

        // call the two-arg constructor first
        // So anything that may be changed in the first constructor 
        // automatically is accounted for in this constructor
        this(amount, inAccount);

        // set the memo
        this.memo = memo;
    }

    /**
     * Get the amount of the transaction
     * @return a double representing the amount
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Get a string summarizing the transaction
     * @return the summary string 
     */
    public String getSummaryLine(){

        if (this.amount >= 0){
            return String.format("\n%s\nDeposited an amount of: $%.02f\nMemo: %s", this.timeStamp.toString(), 
                    this.amount, this.memo);
        } else {
            return String.format("\n%s\nWithdrew an amount of: $(%.02f)\nMemo: %s", this.timeStamp.toString(), 
                    -this.amount, this.memo);
        }
    }

}
