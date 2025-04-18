package SaleManagement_ASM;

import java.time.*;
import java.util.*;
import java.time.format.*;

public class Validator {
    private static String namePattern = "^[a-zA-Z[ ]]{1,50}$";
    private static String IDPattern = "HR\\d{5}";
    private static String datePattern = "dd/MM/yyyy";
    
    public static boolean isIDValid (String ID) throws InputMismatchException {
        if (!ID.matches(IDPattern)) throw new InputMismatchException("Ma so ID khong hop le.");
        return true;
    }
    
    public static boolean isIDUnique (String checkID, ArrayList<Employee> list) throws Exception{
        for (Employee temp: list) {
            if (temp.getiD().equals(checkID))
                throw new Exception("ID bi trung.");
        }
        return true;
    }
    
    public static boolean isNameValid (String name) throws InputMismatchException{
        if (!name.matches(namePattern)) throw new InputMismatchException("Dinh dang ten khong hop le");
        return true;
    }
    
    public static boolean isGender (String gender) {
        if (!gender.equalsIgnoreCase("true") && !gender.equalsIgnoreCase("false")) 
            throw new InputMismatchException("Dinh dang gioi tinh khong hop le");
        return true;
    }
    
    public static boolean isNumPositive (long num) throws Exception{
        if (num < 0) throw new Exception("So phai la so duong");
        return true;
    }
    
    public static boolean isDateValid (String date) throws InputMismatchException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            throw new InputMismatchException("Dinh dang ngay/thang/nam sinh khong hop le");
        }
    }
    
    public static boolean isAdult (String dateOfBirth) throws Exception{
        int currentYear = LocalDate.now().getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        int birthYear = LocalDate.parse(dateOfBirth, formatter).getYear();
        
        if ((currentYear - birthYear) < 18)
            throw new Exception("Khong du 18 tuoi");
        return true;
    }
}
