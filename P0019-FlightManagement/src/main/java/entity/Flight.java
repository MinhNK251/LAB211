package entity;

import util.ConstVars;
import java.time.LocalDateTime;
import input.FlightInputUtil;
import java.util.LinkedList;
import java.util.List;

public final class Flight{
    private ConstVars consts = ConstVars.getInstance();
    
    private static int incrementFlightNum = 0;
    
    private final String flightNum;
    private boolean isUpcoming;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    
    private boolean[][] seats;
    private Passenger[][] passengerSeat;
    private int maxSeats;
    
    private List<Staff> assignedStaff;

    public Flight() {
        this.flightNum = String.format("F%04d", ++incrementFlightNum);
    }
    
    public Flight(String departureCity, String destinationCity, String departureTime, String arrivalTime) {
        this.flightNum = String.format("F%04d", ++incrementFlightNum);
        this.departureCity = departureCity;
        this.arrivalCity = destinationCity;
        this.setDepartureTime(departureTime);
        this.setArrivalTime(arrivalTime);
        this.isUpcoming = determineUpcoming(departureTime);
        this.maxSeats = 60;
        int row = maxSeats / 6;
        this.seats = new boolean[row][maxSeats / row];
        this.passengerSeat = new Passenger[row][maxSeats / row];
        this.assignedStaff = new LinkedList<>();
    }
    
    public Flight(String flightNum, String departureCity, String destinationCity, String departureTime, String arrivalTime) {
        incrementFlightNum++;
        this.flightNum = flightNum;
        this.departureCity = departureCity;
        this.arrivalCity = destinationCity;
        this.setDepartureTime(departureTime);
        this.setArrivalTime(arrivalTime);
        this.isUpcoming = determineUpcoming(departureTime);
        this.maxSeats = 60;
        int row = maxSeats / 6;
        this.seats = new boolean[row][maxSeats / row];
        this.passengerSeat = new Passenger[row][maxSeats / row];
        this.assignedStaff = new LinkedList<>();
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public String getFlightNum() {
        return flightNum;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String destinationCity) {
        this.arrivalCity = destinationCity;
    }

    public String getDepartureTime() {
        return (departureTime == null)? "Null": departureTime.format(consts.TIMEPATTERN);
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = LocalDateTime.parse(departureTime, consts.TIMEPATTERN);
    }

    public String getArrivalTime() {
        return (arrivalTime == null)? "Null": arrivalTime.format(consts.TIMEPATTERN);
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = LocalDateTime.parse(arrivalTime, consts.TIMEPATTERN);
    }
    
    public boolean getUpcoming() {
        return isUpcoming;
    }
    
    public boolean[][] getSeats () {
        return seats;
    }
    
    public Passenger[][] getPassengerSeat () {
        return passengerSeat;
    }
    
    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }
    
    public int getAllocatedNumOfSeats () {
        int counter = 0;
        for (var row: seats) {
            for (var seat: row) {
                if (seat) counter++;
            }
        }
        return counter;
    }

    public List<Staff> getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(List<Staff> assignedStaff) {
        this.assignedStaff = assignedStaff;
    }
    //</editor-fold>
    
    private boolean determineUpcoming (String deparetureTime) {
        return LocalDateTime.parse(deparetureTime, consts.TIMEPATTERN).isAfter(LocalDateTime.now());
    }
    
    @Override
    public String toString() {
        return String.format("%s_%s_%s_%s_%s", 
                getFlightNum(), getDepartureCity(), getArrivalCity(), 
                getDepartureTime(), getArrivalTime());
    }
    
    
    public String getAllocatedSeats () {
        StringBuilder result = new StringBuilder();
        result.append(getFlightNum()).append("_").append(getAllocatedNumOfSeats());
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j]) {
                    result.append("_").append(i * 6 + j);
                    result.append("_").append(passengerSeat[i][j].toString());
                }
            }
        }
        return result.toString();
    }
    
    public boolean isEmpty () {
        return (flightNum == null) || (departureCity == null) || (arrivalCity == null) || (departureTime == null) || (arrivalTime == null);
    }
    
    public void input() {
        String departCity = FlightInputUtil.enterCity("Enter departure city"),
                arrivedCity = FlightInputUtil.enterCity("Enter arrival City"),
                departTime = FlightInputUtil.enterDate("Enter departure time"),
                arrivedTime = FlightInputUtil.enterDate("Enter arrival time");
        
        this.setDepartureCity(departCity);
        this.setArrivalCity(arrivedCity);
        this.setDepartureTime(departTime);
        this.setArrivalTime(arrivedTime);
        this.isUpcoming = determineUpcoming(departTime);
    }
}