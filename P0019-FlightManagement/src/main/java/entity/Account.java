package entity;

public class Account {
    private Passenger passengerInfo;
    private String username;
    private String password;

    public Account() {
    }
    
    public Account (String username, String password) {
        this.username = username;
        this.password = password;
        passengerInfo = new Passenger();
    }

    public Account(String username, String password, Passenger info) {
        this.username = username;
        this.password = password;
        this.passengerInfo = info;
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public Passenger getPassengerInfo() {
        return passengerInfo;
    }

    public void setPassengerInfo(Passenger passengerInfo) {
        this.passengerInfo = passengerInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s_%s_%s", getUsername(), getPassword(), getPassengerInfo());
    }
}
