import java.util.Scanner;

public class ShiftCipher {

    public static void main(String[] args) {
        // create objects for methods and keyboard scanner
        Scanner in = new Scanner(System.in); // for ints
        Scanner in2 = new Scanner(System.in); // for strings
        ShiftCipher tools = new ShiftCipher();

        // welcome the user
        System.out.println("Welcome to the Shift Cipher program!");
        System.out.println("By Antonio Amore for CSIT 270 - Cryptography Project");

        // 0 - go to main menu
        // 1 - encrypt message
        // 2 - decrypt message
        // 3 - exit program
        int option = 0;
        while(option != 3) {
            // main menu
            System.out.println();
            System.out.println("Please make a selection...");
            System.out.println(" [1] Encrypt message");
            System.out.println(" [2] Decrypt message");
            System.out.println(" [3] Exit program");
            System.out.print(" >> ");

            // set option to integer input
            option = in.nextInt();
            System.out.println();
            if(option < 1 || option > 3) {
                // less than 1 or greater than 3? user is a silly goose. 
                option = 0;
                System.out.println("[!] You must choose an option from 1 to 3. Try again. ");
            } else if(option == 1) {
                // encrypt message
                System.out.println(" == [ Encrypt Message ] == ");
                System.out.print(" Cipher Key: ");
                int key = in.nextInt();
                System.out.print(" Message: ");
                String message = in2.nextLine();
                System.out.println(" Encrypted Message: " + tools.encryptMessage(key, message));
            } else if(option == 2) {
                // decrypt message
                System.out.println(" == [ Decrypt Message ] == ");
                System.out.print(" Cipher Key: ");
                int key = in.nextInt();
                System.out.print(" Encrypted Message: ");
                String message = in2.nextLine();
                System.out.println(" Message: " + tools.decryptMessage(key, message));
            } else if(option == 3) {
                // end program
                System.out.println("Goodbye! Thank you for using the program.");
            } else {
                // this shouldn't happen but just in case.
                System.out.println("Something went wrong. Sending you back to the main menu...");
                option = 0;
            }
        }
        // close input scanner to prevent issues
        in.close();
        in2.close();
    }

    // encrypt message
    private String encryptMessage(int key, String decrypted) {
        // create encrypted string
        String encrypted = "";
        // make sure key is between -25 and 25
        if(key > 26) {
            key = key % 26;
        } else if(key < 0) {
            key = (key % 26) + 26;
        }
        // loop through every letter
        for(int i = 0; i < decrypted.length(); i++){ 
            // get the current character
            char ch = decrypted.charAt(i);
            // only shift letters
            if(Character.isLetter(ch)){
                // set new letter to character plus key
                char c = (char)(ch + key);
                // check if the letter is past the end of lower case or upper case alphabet
                if (Character.isLowerCase(ch) && c > 'z' || Character.isUpperCase(ch) && c > 'Z') {
                    // loop new letter back to beginning of alphabet
                    encrypted += (char)(ch - (26 - key));
                } else {
                    // letter is good, add to encrypted string
                    encrypted += c;
                }
            } else {
                // character is non-letter, don't do anything and add it
                encrypted += ch;
            }
        }
        // return encrypted string
        return encrypted;
    }

    // decrypt message
    private String decryptMessage(int key, String encrypted) {
        // create decrypted string
        String decrypted = "";
        // make sure key is between -25 and 25
        if(key > 26) {
            key = key % 26;
        } else if(key < 0) {
            key = (key % 26) + 26;
        }
        // loop through every letter
        for(int i = 0; i < encrypted.length(); i++){ 
            // get the current character
            char ch = encrypted.charAt(i);
            // only shift letters
            if(Character.isLetter(ch)){
                // set new letter to character plus key
                char c = (char)(ch - key);
                // check if the letter is before the beginning of lower case or upper case alphabet
                if (Character.isLowerCase(ch) && c < 'a' || Character.isUpperCase(ch) && c < 'A') {
                    // loop new letter back to end of alphabet
                    decrypted += (char)(ch + (26 - key));
                } else {
                    // letter is good, add to decrypted string
                    decrypted += c;
                }
            } else {
                // character is non-letter, don't do anything and add it
                decrypted += ch;
            }
        }
        // return decrypted string
        return decrypted;
    }

}