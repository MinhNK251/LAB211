package entity_list;

import entity.Flight;
import entity.Passenger;
import interfaces.ICRUDOperations;
import java.util.ArrayList;
import java.util.List;
import util.FileManager;
import view.Menu;

public class FlightsList extends ArrayList<Flight> implements ICRUDOperations<Flight>{
    public FlightsList () {
        loadFlightData();
        loadFlightSeat();
    }
    
    private void loadFlightData () {
        FileManager f = new FileManager("flight");
        while (f.nextLine() != null) {
            this.add(new Flight(f.nextVar(), f.nextVar(), f.nextVar(),
                    f.nextVar(), f.nextVar()));
        }
    }
    
    private void loadFlightSeat () {
        FileManager f = new FileManager("flightseat");
        while (f.nextLine() != null) {
            Flight curr = getByFlightNum(f.nextVar());
            int num = Integer.parseInt(f.nextVar());
            while (num-- > 0) {
                int seatNum = Integer.parseInt(f.nextVar());
                curr.getSeats()[seatNum / 6][seatNum % 6] = true;
                curr.getPassengerSeat()[seatNum / 6][seatNum % 6] = new Passenger(f.nextVar(), f.nextVar());
            }
        }
    }
    
    //<editor-fold desc="CRUD OPERATIONS" defaultstate="collapsed">
    @Override
    public Boolean addNew (Flight newFlight) {
        if (newFlight == null || newFlight.isEmpty())
            return false;
        this.add(newFlight);
        FileManager.autoSaveFile("flight", newFlight.toString());
        return true;
    }

    @Override
    public Boolean deleting (Flight removeFlight) {
        if (removeFlight == null || removeFlight.isEmpty())
            return false;
        this.remove(removeFlight);
        FileManager.saveFile("flight", this);
        return true;
    }

    @Override
    public Boolean updating (Flight updateFlight) {
        updateFlight.input();
        FileManager.saveFile("flight", this);
        return true;
    }

    @Override
    public Boolean showAll() {
        int counter = 0;
        for (var flight: this) {
            System.out.printf("%s. %s\n", ++counter, flight.toString());
        }
        return true;
    }
    
    public boolean showAvailable () {
        int counter = 0;
        for (var flight: this) {
            if (flight.getUpcoming())
                System.out.printf("%s. %s\n", ++counter, flight.toString());
        }
        return true;
    }
    
    public boolean showAvailable (List<Flight> flightList) {
        if (flightList == null || flightList.isEmpty()) {
            Menu.printStatement("THERE IS NO ENTRY");
            return false;
        }
        int counter = 0;
        for (var flight: flightList) {
            if (flight.getUpcoming())
                System.out.printf("%d. %s\n", ++counter, flight.toString());
        }
        return true;
    }

    public Flight getByFlightNum (String flightNum) {
        for (var flight: this) {
            if (flight.getFlightNum().equals(flightNum))
                return flight;
        }
        return null;
    }
    //</editor-fold>
}