package util;

import entity.Account;
import java.time.format.DateTimeFormatter;

public final class ConstVars {
    public final String[] FILENAMES = {
        "FLIGHT", "RESERVATION", "ACCOUNT", "BOARDINGPASS", "FLIGHTSEAT", "STAFF", "ADMIN"
    };
    public final String[] STAFFROLE = {
        "PILOT", "FLIGHT ATTENDANT", "GROUND STAFF"
    };
    public final DateTimeFormatter TIMEPATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public final DateTimeFormatter DATEPATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final String FLIGHTNUMPATTERN = "F\\d{4}";
    public final String CITYNAMEPATTERN = "^[a-zA-Z[ ]]*$";
    
    public final String USERNAMEPATTERN = "^[\\w]{10, 20}$";
    public final String PASSWORDPATTERN = "^[\\D]{7, 20}$";
    public final String NAMEPATTERN = "^[a-zA-Z[ ]]*$";
    public final String CONTACTNUMPATTERN = "^0\\d{9}";
    
    public final Account ADMINISTRATOR = new Account("adminAccount", "123456789");
    
    private static class SingletonHolder {
        public static final ConstVars instance = new ConstVars();
    }
    
    public static ConstVars getInstance () {
        return SingletonHolder.instance;
    }
}