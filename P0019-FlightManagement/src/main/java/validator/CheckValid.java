package validator;

import entity.Account;
import exception.InvalidFileNameExcception;
import exception.InvalidInputException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import util.ConstVars;

/*----------------------------------------------------------------------------------------------
-------The CheckValid class check for the validity of data passed through the parameters--------
-----------------------------------------------------------------------------------------------*/

public class CheckValid{
    static private ConstVars consts = ConstVars.getInstance();    
    
    public static void isFileValid (String fileName) throws InvalidFileNameExcception{
        for (String name: consts.FILENAMES) {
            if (name.equalsIgnoreCase(fileName))
                return;
        }
        throw new InvalidFileNameExcception("There is no such file name!");
    }
    
    public static void isFlightNumValid (String flightNum) throws InvalidInputException {
        if (!flightNum.matches(consts.FLIGHTNUMPATTERN))
            throw new InvalidInputException("Invalid flight number, please try again!");
    }
    
    public static void isCityValid (String cityName) throws InvalidInputException {
        if (!cityName.matches(consts.CITYNAMEPATTERN))
            throw new InvalidInputException("Invalid city name, please try again!");
    }
    
    public static void isTimeValid (String date) throws InvalidInputException {
        try {
            LocalDate.parse(date, consts.TIMEPATTERN);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid time format, please try again!");
        }
    }
    
    public static <T extends Number> void isNumPositive (T num) throws InvalidInputException{
        if (num.toString().compareTo("0") < 0) 
            throw new InvalidInputException("Number should be positive");
    }
    
    public static void isUsernameValid (String username) throws InvalidInputException{
        if (username.matches("\\s+"))
            throw new InvalidInputException("Username cannot contain space!");
        if (username.length() < 10 || username.length() > 50)
            throw new InvalidInputException("Username must be in the range of 10-50!");
        if (username.matches("\\p{Punct}+")) 
            throw new InvalidInputException("Username cannot contain special characters!");
    }
    
    public static void isPasswordValid (String password) throws InvalidInputException {
        if (password.matches("\\s+"))
            throw new InvalidInputException("Password cannot contain space!");
        if (password.length() < 7 || password.length() > 20)
            throw new InvalidInputException("Password must be in the range of 7-20!");
    }
    
    public static void isNameValid (String name) throws InvalidInputException {
        if (!name.matches(consts.NAMEPATTERN))
            throw new InvalidInputException("Invalid passenger name, please try again!");
    }
    
    public static void isContactNumValid (String contact) throws InvalidInputException {
        if (!contact.matches(consts.CONTACTNUMPATTERN)) 
            throw new InvalidInputException("Invalid passenger contact number, please try again!");
    }
    
    public static void isPasswordCorrect (Account account, String password) throws InvalidInputException{
        if (!account.getPassword().equals(password)) 
            throw new InvalidInputException("Invalid password, pleas try again!");
    }
}