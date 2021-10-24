import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class EncryptProgram {
    
    private Scanner input;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char ch;
    private String line;
    private char[] letters;
    
    /**
     * encrypt program constructer
     */
    EncryptProgram() {

        input = new Scanner(System.in);
        random = new Random();
        list = new ArrayList();
        shuffledList = new ArrayList();
        char ch = ' ';
       
        newKey();
        askQuestion();
    }

    /**
     * method to display options menu for program
     */
    private void askQuestion(){

        while (true) {
            System.out.println("\t#########################################");
            System.out.println("\nWhat do you want to do");
            System.out.println("(N)newKey\n(G)getKey\n(E)encrypt\n(Q)quit");
            char response = Character.toUpperCase(input.nextLine().charAt(0));

            switch(response){

                case 'N': 
                    newKey();
                    break;
                case 'G': 
                    getKey();
                    break;
                case 'E': 
                    encrypt();
                    break;
                case 'Q': 
                    quit();
                    break;
                default:
                System.out.println("\tInvalid option!\nPlease chose one of the presented options.\n");
            }
        }

    }
    
    /**
     * method used to generate a new encyrption key
     */
    private void newKey(){

        ch = ' ';
        list.clear(); 
        shuffledList.clear(); // clears list, generating a new key each time.

        for(int i = 32; i < 127; i++){
            list.add(Character.valueOf(ch));
            ch++;
        }

        shuffledList = new ArrayList(list);
        Collections.shuffle(shuffledList); // using the static method shuffle from the collections class
        System.out.println("\nA new key has been generataed.\n");

    }

    /**
     * message used to retrive encryption key
     */
    private void getKey(){
        System.out.println("\nKey: ");
        for(Character c : list){
            System.out.print(c);
        }

        System.out.println("\n");

        for(Character c : shuffledList){
            System.out.print(c);
        }

        System.out.println("\n");
    }

    /**
     * method to encrypt user message
     */
    private void encrypt(){

        System.out.println("Enter a message to be encrypted: ");
        String message = input.nextLine();

        letters = message.toCharArray(); // converting the user's message to a char array

        // Using a nested for loop to changed each char in the user's message
        // with the corresponding index in shffuled list
        for (int i = 0; i < letters.length; i++){ // Looping the length of user input

            for (int j = 0; j < list.size(); j++){ // looping through the entire ascii char list

                if (letters[i] == list.get(j)){ // if index "i" matches index "j" in the ascii char list

                    // replace the char at index "i" in the user message with index "j" from the shuffled list
                    letters[i] = shuffledList.get(j); 
                    break;
                }
            }
        }

        // printing out encrypted message to the screen
        System.out.println("\nEncrypted: ");
        for (char c : letters){
            System.out.print(c);
        }
        System.out.println();

    }

    /**
     * Method to decrypt message
     */
    private void decrypt(){
         
        System.out.println("Enter a message to be decrypted: ");
        String message = input.nextLine();

        letters = message.toCharArray(); // converting the user's message to a char array

        // Using a nested for loop to changed each char in the user's encrypted message
        // with the corresponding index in list
        for (int i = 0; i < letters.length; i++){ // Looping the length of user encrypted message

            for (int j = 0; j < shuffledList.size(); j++){ // looping through the entire  shuffled ascii char list

                if (letters[i] == shuffledList.get(j)){ // if index "i" matches index "j" in the suffled ascii char list

                    // replace the char at index "i" in the user message with index "j" from the unshuffled list
                    letters[i] = list.get(j); 
                    break;
                }
            }
        }

        // printing out encrypted message to the screen
        System.out.println("\nDecrypted: ");
        for (char c : letters){
            System.out.print(c);
        }
        System.out.println();


    }

    /**
     * method to exit program
     */
    private void quit(){

        System.out.println("Thank you have a nice day!");
        System.exit(0);
    }


}
