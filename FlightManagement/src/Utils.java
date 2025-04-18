import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static String getString(String str) {
        System.out.print(str);
        String result = "";
        boolean check = true;
        do {
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty()) {
                result = tmp;
                check = false;
            }
            else System.err.print("Cannot be empty, please re-enter: ");
        } while (check);
        return result;
    }
    
    public static String updateString(String oldValue, String str) {
        String result = oldValue;
        System.out.print(str);
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String str) {
        int number;
        while (true) {
            try {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                number = Integer.parseInt(sc.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.err.println("Wrong data type, please input number! ");
            }
        }
    }
    
    public static int updateInt(int oldValue, String str) {
        int result = oldValue;
        boolean check = true;
        try {
            do {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } while (check);
        } catch (Exception e) {
            System.out.print("Wrong data type, please input number: ");
        }
        return result;
    }

    public static boolean getBoolean(String message) {
        System.out.println(message);
        boolean result = false;
        boolean check = true;
        do {
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            try {
                result = Boolean.parseBoolean(tmp);
                check = false;
            } catch (NumberFormatException E) {
                System.err.print("Wrong data type, please input true/false (1/0)! ");
            }
        } while (check);
        return result;
    }

    public static boolean updateBoolean(boolean oldValue, String message) {
        boolean result = oldValue;
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        try {
            if (!tmp.isEmpty()) {
                result = Boolean.parseBoolean(tmp);
            }
        } catch (NumberFormatException E) {
        }

        return result;
    }

    public static String getDate(String str) {
        System.out.print(str);
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTimeFormat.setLenient(false);
        while (true) {
            Scanner sc = new Scanner(System.in);
            String date = sc.nextLine();
            try {
                dateTimeFormat.parse(date);
                return date;
            } catch (ParseException e) {
                System.out.print("Wrong format, please re-enter(dd/MM/yyyy): ");
            }
        }
    }
    
    public static String updateDate(String oldValue, String str) {
        String result = oldValue;
        System.out.print(str);
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTimeFormat.setLenient(false);
        while (true) {
            Scanner sc = new Scanner(System.in);
            String date = sc.nextLine();
            try {
                if (!date.isEmpty()){
                    dateTimeFormat.parse(date);
                    result = date;
                }
                return result;
            } catch (ParseException e) {
                System.out.print("Wrong format, please re-enter(dd/MM/yyyy): ");
            }
        }
    }
    
    public static String getFlightNumber(String str) {
        int count = 0;
        String flightPattern = "F\\d{4}";
        System.out.print(str);
        String result="";
        boolean check = true;
        do {
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty())
                if (tmp.matches("F\\d{4}")){
                    result = tmp;
                    check = false;
                }
                else {
                    count++;
                }
            else System.err.print("Cannot be empty, please re-enter: ");
        } while (check);
        return result;
    }
    
    public static int getNotNegative(String str) {
        int number;
        while (true) {
            try {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                number = Integer.parseInt(sc.nextLine());
                if (number > -1) {
                    return number;
                } else {
                    System.out.println("Invalid price, please re-enter number >= 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error, please input number! ");
            }
        }
    }
    
    public static int updateNotNegative(int oldValue, String str) {
        int result = oldValue;
        boolean check = true;
        try {
            do {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                result = Integer.parseInt(sc.nextLine());
                if (result > -1) {
                    check = false;
                } else {
                    System.out.println("Invalid value, please re-enter number >= 0!");
                }
            } while (check);
        } catch (Exception e) {
            System.err.print("Error, please input number!");
        }
        return result;
    }
    
    public static boolean getYesNo(String str) {
        System.out.print(str);
        while (true) {
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (tmp.equalsIgnoreCase("Yes"))
                return true;
            if (tmp.equalsIgnoreCase("No"))
                return false;
            System.err.print("Please re-enter (Yes/No): ");
        }
    }
    
    public static String getRole(String str) {
        String result = "";
        boolean check = true;
        do {
            System.out.print(str);
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("pilot") || tmp.equalsIgnoreCase("flight attendant") || tmp.equalsIgnoreCase("ground staff"))) {
                result = tmp;
                check = false;
            } else
                System.err.println("Enter the wrong role, please re-enter (pilot, flight attendant, and ground staff)!");
        } while (check);
        return result;
    }
    
    public static String updateRole(String oldValue, String str) {
        String result = oldValue;
        boolean check = true;
        do {
            System.out.print(str);
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("pilot") || tmp.equalsIgnoreCase("flight attendant") || tmp.equalsIgnoreCase("ground staff"))) {
                result = tmp;
                check = false;
            } else if (tmp.isEmpty())
                check = false;
            else
                System.err.println("Enter the wrong role, please re-enter (pilot, flight attendant, and ground staff)!");
        } while (check);
        return result;
    }
    
    public static boolean checkIdExist(ArrayList<Passenger> list, String iD) {
        for (Passenger p : list)
            if (p.getId().equals(iD))
                return true;
        return false;
    }
    
    public static void printMenu() {
        System.out.println("------- FLIGHT MANAGEMENT SYSTEM -------");
        System.out.println("1. Flight schedule management");
        System.out.println("2. Passenger reservation and booking");
        System.out.println("3. Passenger check-in and seat allocation");
        System.out.println("4. Crew management and assignments");
        System.out.println("5. Administrator access for system management");
        System.out.println("6. Save File");
        System.out.println("7. Load file");
        System.out.println("Other. Exit");
        System.out.println("--------------------------\n");
    }
}