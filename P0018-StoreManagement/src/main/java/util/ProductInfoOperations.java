package util;

import entity.Product;
import exception.InvalidInputException;
import interfaces.IActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import validator.CheckValid;
import interfaces.IConstValue;
import java.util.List;
import validator.CheckAvailable;

/*-------------------------------------------------------------------------------------------------
--ProductInforOperation class stores function for entering product information and product search--
-------------------------------------------------------------------------------------------------*/

public class ProductInfoOperations implements IConstValue, IActionListener{
    //Privately stored product list used to search for product in the list, the list change whenver the subscribed event are fired
    private static List<Product> list;    
    
    //Read user input on product Code and check for the validity and availability of the product
    //existCheckException is used whether for throwing exception of already exist or does not exist of the product
    public static String enterCode (String message, Boolean existCheckException) {
        String input;
        try {
            input = DataOperation.readString(message);
            CheckValid.isProductCodeValid(input);
            if (existCheckException == null) {}
            else if (!(CheckAvailable.doesProductExist(input) ^ existCheckException)) {
                if (existCheckException) 
                    throw new Exception("Product already exist");
                else
                    throw new Exception("Product does not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return enterCode(message, existCheckException);
        }
        return input;
    }
    
    //Read user input on product name and check for its validity
    public static String enterName (String message) {
        String input;
        try {
            input = DataOperation.readString(message);
            CheckValid.isProductNameValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return enterName(message);
        }
        return input;
    }
    
    //Read user input on product quantity and check for its validity
    public static int enterQuantity (String message) {
        int input;
        try {
            input = Integer.parseInt(DataOperation.readString(message));
            CheckValid.isNumPositive(input);
        } catch (InvalidInputException | NumberFormatException e) {
            System.out.println(e.getMessage());
            return enterQuantity(message);
        }
        return input;
    }
    
    //Read user input on two product dates and check for its validity
    public static String enterDate (String message) {
        String input;
        try {
            input = DataOperation.readString(message);
            CheckValid.isTimeValid(input);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return enterDate(message);
        }
        return input;
    }
    
    //Return the type of product based on its difference between manufactured and expired dates
    public static String calculateType(Product curProduct) {
        return (LocalDate.parse(curProduct.getManufacturedD(), DATEPATTERN).until(
                LocalDate.parse(curProduct.getExpirationD(), DATEPATTERN), ChronoUnit.DAYS) <= 5)
                ? "Daily": "Long life";
    }
    
    //Return the index of the search product code, if it does not exist then return -1
    public static int searchByCode (String code) {
        int index = 0;
        for (; index < list.size(); index++) {
            if (list.get(index).getCode().equals(code))
                return index;
        }
        return -1;
    }

    @Override
    public void update(Object newList) {
        list = (List<Product>)newList;
    }
}