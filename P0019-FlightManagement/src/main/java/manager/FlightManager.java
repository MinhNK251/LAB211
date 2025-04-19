package manager;

import entity.Flight;
import entity_list.FlightsList;
import interfaces.ICRUDOperations;
import java.util.LinkedList;
import java.util.List;
import util.DataOperation;
import input.FlightInputUtil;
import view.Menu;

public class FlightManager implements ICRUDOperations<Flight>{
    private FlightsList list;

    public FlightManager() {
        this.list = new FlightsList();
    }
    
    public void flightManagerInterface () {
        while (true) {
            Menu.printMenu(Menu.SEARCHOPTIONS);
            int choice = DataOperation.readInt("Enter your choice");
            switch (choice) {
                case 1 -> {
                    if (list.showAvailable()) 
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 2 -> {
                    String flightNum = FlightInputUtil.enterFlightNum("Enter the flight number", false, list);
                    if (list.showAvailable(searchByFlightNum(flightNum)))
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 3 -> {
                    String departureCity = FlightInputUtil.enterCity("Enter the flight departure city");
                    if (list.showAvailable(searchByDepartCity(departureCity))) 
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 4 -> {
                    String arrivalCity = FlightInputUtil.enterCity("Enter the flight arrival city");
                    if (list.showAvailable(searchByArrivalCity(arrivalCity)))
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 5 -> {
                    String departureTime = FlightInputUtil.enterDate("Enter the flight departure data");
                    if (list.showAvailable(searchByDepartTime(departureTime)))
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 6 -> {
                    return;
                } case 7 -> {
                    for (var flight: list) {
                        System.out.println(flight.getAllocatedSeats());
                    }
                } default -> {
                    Menu.printStatement(Menu.INVALIDCHOICE);
                    flightManagerInterface();
                }
            }
        }
    }

    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public FlightsList getFlightsList() {
        return list;
    }

    public void setFlightsList(FlightsList list) {
        this.list = list;
    }
    //</editor-fold>
    
    //<editor-fold desc="SEARCH ALGORITHMS" defaultstate="collapsed">
    public List<Flight> searchByFlightNum (String input) {
        List<Flight> result = new LinkedList();
        for (var flight: list) {
            if (flight.getFlightNum().equals(input)) {
                result.add(flight);
            }
        }
        return (result.isEmpty())? null: result;
    }
    
    public List<Flight> searchByDepartCity (String input) {
        List<Flight> result = new LinkedList();
        for (var flight: list) {
            if (flight.getDepartureCity().equals(input))
                result.add(flight);
        }
        return (result.isEmpty())? null: result;
    }
    
    public List<Flight> searchByArrivalCity (String input) {
        List<Flight> result = new LinkedList();
        for (var flight: list) {
            if (flight.getArrivalCity().equals(input))
                result.add(flight);
        }
        return (result.isEmpty())? null: result;
    }
    
    public List<Flight> searchByDepartTime (String input) {
        List<Flight> result = new LinkedList();
        for (var flight: list) {
            if (flight.getDepartureTime().equals(input))
                result.add(flight);
        }
        return (result.isEmpty())? null: result;
    }
    //</editor-fold>
    
    //<editor-fold desc="FLIGHT LIST CRUD OPERATIONS WITH USER INPUT" defaultstate="collapsed">
    public boolean addFlight() {
        Flight newFlight = new Flight();
        newFlight.input();
        list.addNew(newFlight);
        return true;
    }
    
    public boolean updateFlight () {
        Flight updateFlight = getFlightAfterEnter("Enter the updated flight number");
        list.updating(updateFlight);
        return true;
    }
    
    public boolean removeFlight () {
        Flight removeFlight = getFlightAfterEnter("Enter the removed flight number");
        Menu.printStatement(Menu.CONTINUEMESSAGE);
        if (DataOperation.readChoice()) {
            list.deleting(removeFlight);
            return true;
        }
        return false;
    }
    
    private Flight getFlightAfterEnter (String message) {
        String flightNum = FlightInputUtil.enterFlightNum(message, false, list);
        return list.getByFlightNum(flightNum);
    }
    //</editor-fold>
}
