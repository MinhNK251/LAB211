package controller;

import manager.Administrator;
import manager.CheckInManager;
import manager.FlightManager;
import manager.LogInManager;
import manager.ReserveManager;
import util.DataOperation;
import util.Event;
import view.Menu;

/*--------------------------------------------------------------------------------------------
----The MainController class is the controller of the application, contain the main method----
---------control the application flow and read user choice for wanted function in menu--------
--------------------------------------------------------------------------------------------*/

public class MainController {
    private static FlightManager flightM = new FlightManager();
    
    private static Event onGetFlights = new Event();
    
    public static void main(String[] args) {
        //onCalledReport.addListener(new Report());
        //onCalledDelete.addListener(new CheckAvailable());
        do {
            
            mainMenuRedirect();
        } while (true);
    }
    
    public static void mainMenuRedirect () {
        Menu.printMenu(Menu.MAINMENU);
        int userChoice = DataOperation.readInt("Enter your choice");
        switch (userChoice) {
            case 1 -> {
                flightM.flightManagerInterface();
            } case 2 -> {
                LogInManager logInManager = new LogInManager(false);
                ReserveManager reserveM = logInManager.getReserveManager();
                if (reserveM != null) {
                    onGetFlights.addListener(reserveM);
                    onGetFlights.invoke(flightM);
                    
                    reserveM.reserveManagerInterface();

                    onGetFlights.removeListener(reserveM);
                }
            } case 3 -> {
                CheckInManager checkInM = new CheckInManager(flightM);
                checkInM.checkInInterface();
            } case 4 -> {
                LogInManager logInManager = new LogInManager(true);
                Administrator admin = logInManager.getAdministrator();
                if (admin != null) {
                    onGetFlights.addListener(admin);
                    onGetFlights.invoke(flightM);
                    
                    admin.administratorInterface();
                    
                    onGetFlights.removeListener(admin);
                }
            } case 5 -> {
                
            } case 6 -> {
                Menu.printStatement(Menu.CONTINUEMESSAGE);
                if (DataOperation.readChoice()) {
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                    System.exit(0);
                } else 
                    return;
            }
            default -> {
                System.out.println("Invalid choice, please try again!");
            }
        }
    }
    /*
    private static void productRedirect () {
        Menu.printMenu(Menu.MANAGEPRODUCTS);
        int userChoice = DataOperation.readInt("Enter your choice");
        switch (userChoice) {
            case 1 -> {
                if (products.addProduct())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            }
            case 2 -> {
                if (products.updateProduct())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);                
            }
            case 3 -> {
                onCalledDelete.invoke(warehouse);
                if (products.deleteProduct())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);                
            }
            case 4 -> {
                if (products.showProducts())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE); 
            }
            case 5 -> {
                return;
            }
            default -> {
                System.out.println("Invalid choice, please try again!");
                productRedirect();
            }
        }
    }
    
    private static void warehouseRedirect () {
        Menu.printMenu(Menu.MANAGEWAREHOUSE);
        int userChoice = DataOperation.readInt("Enter your choice");
        switch (userChoice) {
            case 1 -> {
                if (warehouse.createImport() && receiptProductRedirect())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE); 
            }
            case 2 -> {
                if (warehouse.createExport() && receiptProductRedirect())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE); 
            }
            case 3 -> {
                if (warehouse.showReceipt())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE); 
            }
            case 4 -> {
                return;
            }
            default -> {
                System.out.println("Invalid choice, please try again!");
                warehouseRedirect();
            }
        }
    }
    
    private static Boolean receiptProductRedirect () {
        Receipt currentReceipt = warehouse.getLatestReceipt();
        do {
            Menu.printMenu(Menu.RECEIPTMENU);
            int userChoice = DataOperation.readInt("Enter your choice");
            switch (userChoice) {
                case 1 -> {
                    if (currentReceipt.addProduct())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else
                        Menu.printStatement(Menu.FAILSTATE);
                }
                case 2 -> {
                    if (currentReceipt.updateProduct())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else
                        Menu.printStatement(Menu.FAILSTATE);
                }
                case 3 -> {
                    if (currentReceipt.deleteProduct())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else
                        Menu.printStatement(Menu.FAILSTATE);
                }
                case 4 -> {
                    if (currentReceipt.showProducts())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else
                        Menu.printStatement(Menu.FAILSTATE);
                }
                case 5 -> {
                    return currentReceipt.confirmReceipt(products);
                }
                default -> {
                    System.out.println("Invalid choice, please try again!");
                }
            }
        } while (true);
    }
    
    private static void reportRedirect () {
        Menu.printMenu(Menu.REPORT);
        int userChoice = DataOperation.readInt("Enter your choice");
        switch (userChoice) {
            case 1 -> {
                onCalledReport.invoke(products);
                if (Report.printReport(Report.getExpiredProducts()))
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            }
            case 2 -> {
                onCalledReport.invoke(products);
                if (Report.printReport(Report.getSellingProducts()))
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            }
            case 3 -> {
                onCalledReport.invoke(products);
                if (Report.printReport(Report.getOutOfStockProducts()))
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            }
            case 4 -> {
                onCalledReport.invoke(warehouse);
                if (Report.printReport(Report.getProductReceipt()))
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);  
            }
            case 5 -> {
                return;
            }
            default -> {
                System.out.println("Invalid choice, please try again!");
                reportRedirect();
            }
        }
    }
    */
}