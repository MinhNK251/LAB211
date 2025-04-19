package input;

import entity.Reservation;
import exception.InvalidInputException;
import java.util.List;
import util.DataOperation;
import validator.CheckAvailable;

public class ReserveInputUtil {
    public static String enterReserveID (String message, List<Reservation> list) {
        String input;
        try {
            input = DataOperation.readString(message);
            CheckAvailable.doesReservationExist(list, message);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return enterReserveID(message, list);
        }
        return input;
    }
}
