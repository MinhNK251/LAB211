package input;

import exception.InvalidInputException;
import util.ConstVars;
import util.DataOperation;
import validator.CheckValid;
import view.Menu;

public class StaffInputUtil {
    private static ConstVars consts = ConstVars.getInstance();
    
    public static String enterStaffName (String message) {
        String input = null;
        try {
            input = DataOperation.readString(message);
            CheckValid.isNameValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            enterStaffName(message);
        }
        return DataOperation.firstLetterUpper(input);
    }
    
    public static int enterStaffAge (String message) {
        int input = Integer.MIN_VALUE;
        try {
            input = DataOperation.readInt(message);
            CheckValid.isNumPositive(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            enterStaffAge(message);
        }
        return input;
    }
    
    public static String enterStaffRole () {
        Menu.printMenu(Menu.STAFFCHOICEOPTIONS);
        int choice = DataOperation.readInt("Enter staff role number");
        switch (choice)  {
            case 1 -> {
                return consts.STAFFROLE[0];
            } case 2 -> {
                return consts.STAFFROLE[1];
            } case 3 -> {
                return consts.STAFFROLE[2];
            } default -> {
                Menu.printStatement(Menu.INVALIDCHOICE);
                enterStaffRole();
            }
        }
        return null;
    }
}
