package entity;

import java.util.Random;
import java.util.UUID;

public class BoardingPass {    
    private String uniqueID;
    private Passenger passengerInfo;
    private Flight flightInfo;
    private String allocatedSeat;
    
    public BoardingPass (Passenger passenger, Flight flight) {
        this.passengerInfo = passenger;
        this.flightInfo = flight;
        this.allocatedSeat = null;
        this.uniqueID = UUID.randomUUID().toString().substring(0, 13);
    }
    
    public BoardingPass (String uniqueID, Passenger passenger, String allocatedSeat, Flight flight) {
        this.uniqueID = uniqueID;
        this.passengerInfo = passenger;
        this.allocatedSeat = allocatedSeat;
        this.flightInfo = flight;
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public String getUniqueID() {
        return uniqueID;
    }

    public Passenger getPassengerInfo() {
        return passengerInfo;
    }

    public void setPassengerInfo(Passenger passengerInfo) {
        this.passengerInfo = passengerInfo;
    }

    public Flight getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(Flight flightInfo) {
        this.flightInfo = flightInfo;
    }

    public String getAllocatedSeat() {
        return allocatedSeat;
    }

    public void setAllocatedSeat(String allocatedSeat) {
        this.allocatedSeat = allocatedSeat;
    }
    //</editor-fold>
    
    @Override
    public String toString() {
        return String.format("%s_%s_%s_%s", getUniqueID(), getPassengerInfo(), getAllocatedSeat(), getFlightInfo());
    }
    
    public void printPass() {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("\t\t\tBOARDING PASS");
        System.out.println(String.format("%s:\t%s_%s\n\t\t%s", getUniqueID(), getPassengerInfo(), getAllocatedSeat(), getFlightInfo()));
    }
}
