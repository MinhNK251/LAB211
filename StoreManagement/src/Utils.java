import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Utils {
    
    static int num = 0;

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
    
    public static String getType(String str) {
        System.out.print(str);
        String result = "";
        boolean check = true;
        do {
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("import") || tmp.equalsIgnoreCase("export"))) {
                result = tmp;
                check = false;
            } else
                System.out.println("Enter the wrong receipt type, please re-enter (import/export)!");
        } while (check);
        return result;
    }
    
    public static String updateType(String oldValue, String str) {
        String result = oldValue;
        System.out.print(str);
        Scanner sc = new Scanner(System.in);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty() && (tmp.equalsIgnoreCase("import") || tmp.equalsIgnoreCase("export")))
            result = tmp;
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
        System.out.println("1. Manage products");
        System.out.println("2. Manage Warehouse");
        System.out.println("3. Report");
        System.out.println("4. Saving to file");
        System.out.println("5. Load file");
        System.out.println("Other. Exit");
        System.out.println("--------------------------\n");
    }
}