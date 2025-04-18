import java.io.Serializable;
import java.util.ArrayList;

public class Flight implements Serializable  {

    private String flightNumber ,departureCity, destinationCity, departureTime, arrivalTime;;
    private int availableSeats;
    private ArrayList<Passenger> list = new ArrayList<>();
    private ArrayList<CrewMember> cList = new ArrayList<>();

    public Flight() {
    }

    public Flight(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public ArrayList<Passenger> getList() {
        return list;
    }

    public void setList(ArrayList<Passenger> list) {
        this.list = list;
    }

    public ArrayList<CrewMember> getcList() {
        return cList;
    }

    public void setcList(ArrayList<CrewMember> cList) {
        this.cList = cList;
    }
    
    protected void createFlight() {
        this.flightNumber = Utils.getString("Flight number (Fxxxx): ");
        this.departureCity = Utils.getString("Departure city: ");
        this.destinationCity = Utils.getString("Destination city: ");
        this.departureTime = Utils.getDate("Departure time: ");
        this.arrivalTime = Utils.getDate("Arrival time: ");
        this.availableSeats = Utils.getInt("Available seats: ");
    }

    protected void updateFlight() {
        this.flightNumber = Utils.updateString(this.flightNumber,"Update flight number (Fxxxx): ");
        this.departureCity = Utils.updateString(this.departureCity, "Update departure city: ");
        this.destinationCity = Utils.updateString(this.destinationCity, "Update destination city: ");
        this.departureTime = Utils.updateDate(this.departureTime, "Update departure time(dd/MM/yyyy): ");
        this.arrivalTime = Utils.updateDate(this.arrivalTime, "Update arrival time(dd/MM/yyyy): ");
        this.availableSeats = Utils.updateInt(this.availableSeats, "Update available seats: ");
    }

    @Override
    public String toString() {
        return "Flight number - " + this.flightNumber + " || Departure city - " + this.departureCity
                + " || Destination city - " + this.destinationCity + " || Departure time - " + this.departureTime
                + " || Arrival time - " + this.arrivalTime + " || Available seats - " + this.availableSeats;
    }

    @Override
    public boolean equals(Object obj) {
        return this.flightNumber.equals(((Flight) obj).getFlightNumber ());
    }
}