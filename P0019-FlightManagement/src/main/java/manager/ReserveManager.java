package manager;

import controller.MainController;
import entity.Flight;
import entity.Passenger;
import entity.Reservation;
import entity_list.ReserveList;
import interfaces.IActionListener;
import util.DataOperation;
import input.FlightInputUtil;
import input.ReserveInputUtil;
import java.util.List;
import util.FileManager;
import view.Menu;


public class ReserveManager implements IActionListener<FlightManager>{
    private final ReserveList list;
    private final Passenger passenger;
    private final List<Reservation> userReservedList;
    private FlightManager flightM = null;
    
    public ReserveManager (Passenger passenger) {
        this.list = new ReserveList();
        this.passenger = passenger;
        this.userReservedList = list.getAllSpecificReserve(passenger);
    }
    
    public void reserveManagerInterface () {
        boolean isContinued = true;
        do {
            Menu.printMenu(Menu.BOOKINGOPTIONS);
            int choice = DataOperation.readInt("Enter user choice");
            switch (choice) {
                case 1 -> {
                    if (addReservation()) 
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 2 -> {
                    if (updateReservation())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 3 -> {
                    if (removeReservation())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 4 -> {
                    if (showAllReservation())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 5 -> {
                    isContinued = false;
                } default -> {
                    Menu.printStatement(Menu.INVALIDCHOICE);
                    reserveManagerInterface();
                }
            }
        } while (isContinued);
        FileManager.saveFile("reservation", list);
        MainController.mainMenuRedirect();
    }
    
    private Flight confirmSearchInterface () {
        Menu.printMenu(Menu.CONFIRMSEARCH);
        int choice = DataOperation.readInt("Enter user choice");
        switch (choice) {
            case 1 -> {
                return flightM.getFlightsList().getByFlightNum(
                        FlightInputUtil.enterFlightNum("Enter your reserving flight number", false, flightM.getFlightsList())
                );
            } case 2 -> {
                flightM.flightManagerInterface();
                confirmSearchInterface();
            } case 3 -> {
                return null;
            } default -> {
                Menu.printStatement(Menu.INVALIDCHOICE);
                confirmSearchInterface();
            }
        }
        return null;
    }
    
    //<editor-fold desc="RESERVATION LIST CRUD OPERRATION WITH USER INPUT" defaultstate="collapsed">
    public boolean addReservation () {
        flightM.flightManagerInterface();
        Flight reservedFlight = confirmSearchInterface();
        if (reservedFlight == null) 
            return false;
        Reservation newReserve = new Reservation(passenger.getName(), passenger.getContactNum(), reservedFlight.getFlightNum());
        if (list.addNew(newReserve)) {
            userReservedList.add(newReserve);
            System.out.println("Your new reservation information: " + newReserve);
            return true;
        }
        return true;
    }
    
    public boolean updateReservation () {
        showAllReservation();
        String id = ReserveInputUtil.enterReserveID("Enter update reserve ID", list);
        Reservation updated = list.getByID(id);
        list.deleting(updated);
        userReservedList.remove(updated);
        System.out.println("Your updated reservation: " + updated);
        return addReservation();
    }
    
    public boolean removeReservation () {
        showAllReservation();
        String id = ReserveInputUtil.enterReserveID("Enter remove reserve ID", userReservedList);
        Menu.printStatement(Menu.CONTINUEMESSAGE);
        
        if (!DataOperation.readChoice())
            return false;
        
        Reservation removed = list.getByID(id);
        list.deleting(removed);
        userReservedList.remove(removed);
        return true;
    }
    
    public boolean showAllReservation () {
        int counter = 0;
        for (var reserve: userReservedList) {
            System.out.printf("%d. %s.\n", ++counter, reserve);
        }
        return true;
    }
    //</editor-fold>
    
    @Override
    public void update(FlightManager flightM) {
        this.flightM = flightM;
    }
}
