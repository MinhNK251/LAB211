package input;

import exception.InvalidInputException;
import util.DataOperation;
import validator.CheckValid;

public class AccountInputUtil {
    public static String enterUsername (String message) {
        String input = null;
        try {
            input = DataOperation.readString(message);
            CheckValid.isUsernameValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            enterUsername(message);
        }
        return input;
    }
    
    public static String enterPassword (String message) {
        String input = null;
        try {
            input = DataOperation.readString(message);
            CheckValid.isPasswordValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            enterPassword(message);
        }
        return input;
    }
    
    public static void reEnterPassword (String message, String org) {
        String input;
        try {
            input = DataOperation.readString(message);
            CheckValid.isPasswordValid(input);
            if (!org.equals(input)) 
                throw new InvalidInputException("Re-entered password is not the same");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            reEnterPassword(message, org);
        }
    }
    
    public static String enterPassengerName (String message) {
        String input = null;
        try {
            input = DataOperation.readString(message);
            CheckValid.isNameValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            enterPassengerName(message);
        }
        return DataOperation.firstLetterUpper(input);
    }
    
    public static String enterContactNum (String message) {
        String input = null;
        try {
            input = DataOperation.readString(message);
            CheckValid.isContactNumValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            enterContactNum(message);
        }
        return input;
    }
}
