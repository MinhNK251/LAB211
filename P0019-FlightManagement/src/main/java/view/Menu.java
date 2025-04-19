package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*------------------------------------------------------------------------------
---------Menu class hold information for what to print for the menu and---------
---------------------methods for printing menu or statement---------------------
------------------------------------------------------------------------------*/

public class Menu {
    //<editor-fold desc="MENU INFORMATION" defaultstate="collapsed">
    static final public List<String> MAINMENU = new ArrayList(Arrays.asList(
            "Search flights",
            "Make a reservation",
            "Check-in and seat allocation",
            "Log in as Administrator",
            "Save to file",
            "Exit"
    ));
    
    static final public List<String> SEARCHOPTIONS = new ArrayList(Arrays.asList(
            "Print all flights",
            "Search by flight num",
            "Search by departure city",
            "Search by arrival city",
            "Search by departure time",
            "Return"
    ));
    
    static final public List<String> CONFIRMSEARCH = new ArrayList(Arrays.asList(
            "Make a reservation",
            "Continue searching",
            "Return to main menu"
    ));
    
    static final public List<String> BOOKINGOPTIONS = new ArrayList(Arrays.asList(
            "Book a seat",
            "Update reservation information",
            "Cancel reservation",
            "Show all available reservations",
            "Return"
    ));
    
    static final public List<String> CHECKINOPTIONS = new ArrayList(Arrays.asList(
            "Change seat",
            "Confirm creating boarding pass",
            "Return to main menu"
    ));
    
    static final public List<String> ADMINISTRATOROPTIONS = new ArrayList(Arrays.asList(
            "View all flights information",
            "Add new flight",
            "Update flight information",
            "Delete flight",
            "Manage staffs ",
            "Change administrator account",
            "Return"
    ));
    
    static final public List<String> STAFFMANAGEMENTOPTIONS = new ArrayList(Arrays.asList(
            "Add new staff",
            "Update staff information",
            "Remove staff",
            "Show all staffs",
            "Assign staff to flight",
            "Return"
    ));
    
    static final public List<String> ASSIGNSTAFFOPTIONS = new ArrayList(Arrays.asList(
            "Assign staff to flight",
            "Remove staff from flight",
            "Return"
    ));
    
    static final public List<String> STAFFCHOICEOPTIONS = new ArrayList(Arrays.asList(
            "Pilot",
            "Flight attendant",
            "Ground staff"
    ));
    
    static final public List<String> LOGINOPTIONS = new ArrayList<>(Arrays.asList(
            "Log In",
            "Register new account",
            "Return"
    ));
    //</editor-fold>
    
    //<editor-fold desc="STATEMENT INFORMATION" defaultstate="collapsed">
    static final public String CONTINUEMESSAGE = "DO YOU WISH TO CONTINUE? Y/N";
    static final public String SUCCESSFULSTATE = "THE OPERATION WAS A SUCCESS";
    static final public String FAILSTATE = "THE OPERATION WAS A FAILURE";
    static final public String SUCCESSFULREGISTER = "SUCCESSFULLY REGISTER, PLEASE LOG IN YOUR ACCOUNT";
    static final public String SUCCESSFULLOGIN = "SUCCESSFULLY LOGGING IN";
    static final public String INVALIDCHOICE = "INVALID USER CHOICE, PLEASE TRY AGAIN!";
    //</editor-fold>
    
    static public void printStatement (String choice) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
        System.out.println(choice);
    }
    
    static public void printMenu (List<String> menu) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
        int counter = 0;
        for (String choice: menu) {
            System.out.printf("%d. %s. \n", ++counter, choice);
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
    }
}