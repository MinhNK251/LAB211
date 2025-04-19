package input;

import entity.Flight;
import exception.InvalidInputException;
import java.util.List;
import validator.CheckValid;
import util.DataOperation;
import validator.CheckAvailable;

/*-------------------------------------------------------------------------------------------------
--ProductInforOperation class stores function for entering product information and product search--
-------------------------------------------------------------------------------------------------*/

public class FlightInputUtil{
    //Read user input on product Code and check for the validity and availability of the product
    //existCheckException is used whether for throwing exception of already exist or does not exist of the product
    public static String enterFlightNum (String message, Boolean existCheckException, List<Flight> list) {
        String input;
        try {
            input = DataOperation.readString(message);
            CheckValid.isFlightNumValid(input);
            if (existCheckException == null) {}
            else if (!((CheckAvailable.doesFlightExist(list, input) != null) ^ existCheckException)) {
                if (existCheckException) 
                    throw new Exception("Flight number already exist");
                else
                    throw new Exception("Flight number does not exist");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return enterFlightNum(message, existCheckException, list);
        }
        return DataOperation.firstLetterUpper(input);
    }
    
    public static String enterCity (String message) {
        String input;
        try {
            input = DataOperation.readString(message);
            CheckValid.isCityValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return enterCity(message);
        }
        return DataOperation.firstLetterUpper(input);
    }
    
    //Read user input on two product dates and check for its validity
    public static String enterDate (String message) {
        String input;
        try {
            input = DataOperation.readString(message + " (dd-MM-yyyy HH:mm)");
            CheckValid.isTimeValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return enterDate(message);
        }
        return input;
    }
}