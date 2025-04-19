package validator;

import exception.InvalidFileNameExcception;
import exception.InvalidInputException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import interfaces.IConstValue;

/*----------------------------------------------------------------------------------------------
-------The CheckValid class check for the validity of data passed through the parameters--------
-----------------------------------------------------------------------------------------------*/

public class CheckValid implements IConstValue{
    public static void isFileValid (String fileName) throws InvalidFileNameExcception{
        for (String name: FILENAMES) {
            if (name.equalsIgnoreCase(fileName))
                return;
        }
        throw new InvalidFileNameExcception("There is no such file name!");
    }
    
    public static void isWarehouseCodeValid (String warehouseCode) throws InvalidInputException {
        if (!warehouseCode.matches(WAREHOUSECODEPATTERN))
            throw new InvalidInputException("Invalid warehouse code, please try again!");
    }
    
    public static void isProductCodeValid (String productCode) throws InvalidInputException {
        if (!productCode.matches(PRODUCTCODEPATTERN))
            throw new InvalidInputException("Invalid product code, please try again!");
    }
    
    public static void isProductNameValid (String productName) throws InvalidInputException {
        if (!productName.matches(PRODUCTNAMEPATTERN))
            throw new InvalidInputException("Invalid product name, please try again!");
    }
    
    public static void isTimeValid (String date) throws InvalidInputException {
        try {
            LocalDate.parse(date, DATEPATTERN);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid time format, please try again!");
        }
    }
    
    public static <T extends Number> void isNumPositive (T num) throws InvalidInputException{
        if (num.toString().compareTo("0") < 0) 
            throw new InvalidInputException("Number should be positive");
    }
}