import java.time.ZonedDateTime;
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

    public static int getYear(String str) {
        int number;
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        int yearCurrent = zonedDateTime.getYear();
        while (true) {
            try {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                number = Integer.parseInt(sc.nextLine());
                if (number < yearCurrent) {
                    return number;
                } else {
                    System.out.println("Invalid year, please re-enter!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error, please input number! ");
            }
        }
    }

    public static int updateYear(int oldValue, String str) {
        int result = oldValue;
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        int yearCurrent = zonedDateTime.getYear();
        boolean check = true;
        try {
            do {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                result = Integer.parseInt(sc.nextLine());
                if (result < yearCurrent) {
                    check = false;
                } else {
                    System.out.println("Invalid year, please re-enter!");
                }
            } while (check);
        } catch (Exception e) {
            System.out.print("Input number: ");
        }
        return result;
    }

    public static String getColor(String str) {
        System.out.print(str);
        String result = "";
        boolean check = true;
        do {
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("blue") || tmp.equalsIgnoreCase("green") || tmp.equalsIgnoreCase("red"))) {
                result = tmp;
                check = false;
            } else {
                System.out.println("Enter the wrong color, please re-enter (Blue/Green/Red)!");
            }

        } while (check);
        return result;
    }

    public static String updateColor(String oldValue, String str) {
        String result = oldValue;
        System.out.print(str);
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("blue") || tmp.equalsIgnoreCase("green") || tmp.equalsIgnoreCase("red")))
            result = tmp;
        return result;

    }
    
    public static int getGreaterThan0(String str) {
        int number;
        while (true) {
            try {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                number = Integer.parseInt(sc.nextLine());
                if (number >0) {
                    return number;
                } else {
                    System.out.println("Invalid value, please re-enter number > 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error, please input number! ");
            }
        }
    }
    
    public static int updateGreaterThan0(int oldValue, String str) {
        int result = oldValue;
        boolean check = true;
        try {
            do {
                System.out.print(str);
                Scanner sc = new Scanner(System.in);
                result = Integer.parseInt(sc.nextLine());
                if (result > 0) {
                    check = false;
                } else {
                    System.out.println("Invalid value, please re-enter number > 0!");
                }
            } while (check);
        } catch (Exception e) {
            System.err.print("Error, please input number!");
        }
        return result;
    }
    
    public static String getType(String str) {
        String result = "";
        boolean check = true;
        do {
            System.out.print(str);
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("sport") || tmp.equalsIgnoreCase("travel") || tmp.equalsIgnoreCase("common"))) {
                result = tmp;
                check = false;
            } else {
                System.err.println("Enter the wrong type, please re-enter (Sport/Travel/Common)!");
            }

        } while (check);
        return result;
    }
    
    public static String updateType(String oldValue, String str) {
        String result = oldValue;
        boolean check = true;
        do {
            System.out.print(str);
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("sport") || tmp.equalsIgnoreCase("travel") || tmp.equalsIgnoreCase("common"))) {
                result = tmp;
                check = false;
            } else if (tmp.isEmpty())
                check = false;
            else {
                System.err.println("Enter the wrong type, please re-enter (Sport/Travel/Common)!");
            }
        } while (check);
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
    
    public static void printMenu() {
        System.out.println("------- TABLE MENU -------");
        System.out.println("0. Add New Vehicle");
        System.out.println("1. Checking Exist Vehicle");
        System.out.println("2. Update Vehicle");
        System.out.println("3. Delete Vehicle");
        System.out.println("4. Search Vehicle");
        System.out.println("5. Display Vehicle lists");
        System.out.println("6. Saving Vehicle to file");
        System.out.println("7. Printing List Vehicles the file");
        System.out.println("8. Load file");
        System.out.println("Other. Exit");
        System.out.println("--------------------------\n");
    }
}