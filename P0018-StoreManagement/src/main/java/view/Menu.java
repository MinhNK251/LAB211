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
            "Manage products", 
            "Manage warehouse", 
            "Report", 
            "Save file",
            "Exit")
    );
    
    static final public List<String> MANAGEPRODUCTS = new ArrayList(Arrays.asList(
            "Add a product", 
            "Update product information", 
            "Delete product", 
            "Show all product",
            "Return to main menu"
    ));
    
    static final public List<String> MANAGEWAREHOUSE = new ArrayList(Arrays.asList(
            "Create an import receipt",
            "Create an export receipt",
            "Show all receipt",
            "Return to main menu"
    ));
    
    static final public List<String> RECEIPTMENU = new ArrayList(Arrays.asList(
            "Add a product", 
            "Update product information", 
            "Delete product", 
            "Show all product",
            "Confirm receipt"
    ));
    
    static final public List<String> REPORT = new ArrayList(Arrays.asList(
            "Products that have been expired", 
            "The products that the store is selling", 
            "Products that are running out of stock",
            "Import/Export receipt of a product",
            "Return to main menu"
    ));
    //</editor-fold>
    
    //<editor-fold desc="STATEMENT INFORMATION" defaultstate="collapsed">
    static final public String CONTINUEMESSAGE = "DO YOU WISH TO CONTINUE? Y/N";
    static final public String SUCCESSFULSTATE = "THE OPERATION WAS A SUCCESS";
    static final public String FAILSTATE = "THE OPERATION WAS A FAILURE";
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