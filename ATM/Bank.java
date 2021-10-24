import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * Creates a new Bank object with empty lists of users and accounts
     * @param name the name of the bank
     */
    public Bank(String name){

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * 
     * @return
     */
    public String getNewUserUUID(){
        String uuid;
        Random rng = new Random(); // random # generator
        int len = 6; // length of uuid
        boolean nonUnique; // boolean to represent wether its unique or not

        // continueing looping until we get a unique ID
        do {

            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++){
                // a random number between 0 and 10 is being cast as an Integer object 
                // in order to use the toString method and being append on to the end of uuid
                uuid += ((Integer)rng.nextInt(10)).toString();

            }

            //check to make sure it's unique
            nonUnique = false;
            for (User u : this.users){
                // if the uuid generated does not match and other uuid already within 
                // the User object list than it is unique
                if(uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while(nonUnique);

        return uuid;

    }

    public String getNewAccountUUID(){

        String uuid;
        Random rng = new Random(); // random # generator
        int len = 10; // length of uuid
        boolean nonUnique; // boolean to represent wether or not uuid is unique

        // continueing looping until we get a unique ID
        do {

            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++){
                // a random number between 0 and 10 is being cast as an Integer object 
                // in order to use the toString method and being append on to the end of uuid
                uuid += ((Integer)rng.nextInt(10)).toString();

            }

            //check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts){
                // if the uuid generated does not match and other uuid already within 
                // the User object list than it is unique
                if(uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while(nonUnique);

        return uuid;

    }

    /**
     * Add an account
     * @param anAcct the account to add
     */
   public void addAccount(Account anAcct) {
       this.accounts.add(anAcct);
   }


    /**
     * create a new user of the bank
     * @param firstName First name of the user
     * @param lastName Last name of the user
     * @param pin Pin of the user
     * @return the new User object
     */
    public User addUser(String firstName, String lastName,  String pin){

        // create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account for the new user and add it to the User
        // and Bank accounts lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    /**
     * Get the User object associated with a particular userID and pin, if
     * they are valid
     * @param userID the UUID of the user to log in
     * @param pin the pin of the user
     * @return the User object, if the login is succesful, or null
     * if it is not
     */
    public User userLogin(String userID, String pin){

        // search through the list of users
        for (User u : this.users){

            // check user ID  is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }

        // if we haven't found the user or have an incorrect pin
        return null;
    }

    /**
     *  Gets name of bank
     * @return bank's name
     */
    public String getName(){
        return this.name;
    }
    
}
