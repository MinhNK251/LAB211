package util;

import java.util.Scanner;

/*------------------------------------------------------------------------------------------------------
--DataOperation Class read user input, check for blank/empty input, and convert input to wanted format--
------------------------------------------------------------------------------------------------------*/

public class DataOperation{
    private static final Scanner sc = new Scanner(System.in);
    
    //Read string input until there is data input by the user, then format it
    public static String readString (String message) {
        String input;
        System.out.printf("%s: ", message);
        do {
            input = sc.nextLine();
        } while (input.isBlank() || input.isEmpty());
        return firstLetterUpper(input);
    }
    
    //Read int input until there is data input by the user and is of type integer
    public static int readInt (String message) {
        int input = 0;
        System.out.printf("%s: ", message);        
        do {
            try {
            input = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
            }
        } while (Integer.toString(input).isBlank() || Integer.toString(input).isEmpty());
        return input;
    }
    
    //Read user choice, only return true when user input "yes", "y", "t", "true".
    public static Boolean readChoice () {
        String input;
        do {
            input = sc.nextLine();
        } while (input.isBlank());
        
        return (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y") || 
                input.equalsIgnoreCase("t") || input.equalsIgnoreCase("true"));
    }
    
    //Format user string input with uppercase for the first letter of each word
    private static String firstLetterUpper (String str) {
        String[] strArr = str.trim().split("\\s+");
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = strArr[i].substring(0, 1).toUpperCase() +
                    strArr[i].substring(1).toLowerCase();
        }
        return String.join(" ", strArr);
    }
}