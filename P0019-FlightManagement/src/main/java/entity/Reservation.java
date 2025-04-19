package entity;

import util.ConstVars;
import java.time.LocalDateTime;
import java.util.UUID;

public final class Reservation{
    private ConstVars consts = ConstVars.getInstance();
    
    private Passenger passenger;
    private String flightNum;
    private LocalDateTime createdTime;
    private String reserveID;
    
    /*
    public Reservation () {
        this.passenger = new Passenger();
        this.createdTime = LocalDateTime.now();
        this.reserveID = UUID.randomUUID().toString().substring(0, 8);
    }*/
    
    public Reservation(String name, String contactNum, String flightNum) {
        this.passenger = new Passenger(name, contactNum);
        this.flightNum = flightNum;
        this.createdTime = LocalDateTime.now();
        this.reserveID = UUID.randomUUID().toString().substring(0, 8);
    }
    
    public Reservation (String reserveID, String name, String flightNum, String contactNum, String createdTime) {
        this.reserveID = reserveID;
        this.passenger = new Passenger(name, contactNum);
        this.flightNum = flightNum;
        this.setCreatedTime(createdTime);
    }

    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public Passenger getPassenger() {    
        return passenger;
    }
    
    public void setPassenger(Passenger newPassenger) {
        this.passenger = newPassenger;
    }

    public String getCreatedTime() {
        return createdTime.format(consts.TIMEPATTERN);
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = LocalDateTime.parse(createdTime, consts.TIMEPATTERN);
    }

    public String getReserveID() {
        return reserveID;
    }
    
    public String getFlightNum () {
        return flightNum;
    }
    
    public void setFlightNum (String flightNum) {
        this.flightNum = flightNum;
    }
    //</editor-fold>

    public boolean isEmpty() {
        return passenger.getName().isBlank() || passenger.getContactNum().isEmpty();
    }
    
    @Override
    public String toString() {
        return String.format("%s_%s_%s_%s_%s", getReserveID(), passenger.getName(), getFlightNum(), passenger.getContactNum() , getCreatedTime());
    }
}
