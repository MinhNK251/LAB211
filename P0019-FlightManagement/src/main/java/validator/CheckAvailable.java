package validator;

import entity.Account;
import entity.Flight;
import entity.Reservation;
import entity_list.AccountsList;
import exception.InvalidInputException;
import java.util.List;

/*--------------------------------------------------------------------------------------------------
--CheckAvailable Class checks for the availability of a product using its code in the main product--
------------------------------------list or receipt product list------------------------------------
--------------------------------------------------------------------------------------------------*/

public class CheckAvailable{
    public static Account doesAccountExist (String username, AccountsList list) throws InvalidInputException{
        for (var account: list) {
            if (account.getUsername().equals(username)) 
                return account;
        }
        throw new InvalidInputException("Account does not exist");
    }
    
    public static Reservation doesReservationExist (List<Reservation> list, String reserveID) throws InvalidInputException{
        for (var reserve: list) {
            if (reserve.getReserveID().equals(reserveID)) 
                return reserve;
        }
        throw new InvalidInputException("Reservation does not exist!");
    }
    
    public static Flight doesFlightExist (List<Flight> list, String flightID) throws InvalidInputException {
        for (var flight: list) {
            if (flight.getFlightNum().equals(flightID))
                return flight;
        }
        return null;
    }
    
    /*
    //Privately stored product list that changed every time the subscribed event fired
    private static ProductList pList;
    private static Warehouse wList;
    
    public static List<Product> getList () {
        return pList;
    }
    
    //Check for the existence of the product based on its product code in the product list
    public static Boolean doesProductExist (String code) {
        for (Product temp: pList) {
            if (temp.getCode().equals(code))
                return true;
        }
        return false;
    }
    
    //Check for the appearance of a product in any of the receipt.
    //If there is then return true, otherwise, false
    public static Boolean doesProductInReceipt (String code) {
        for (var receipt: wList) {
            for (var product: receipt.getProductList()) {
                if (product.getCode().equals(code)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //Update function override from IActionListener Interface, update the list stored in the class 
    //when the subscribed event fired
    @Override
    public void update(Object newList) {
        if (newList instanceof ProductList productList)
            pList = productList;
        else if (newList instanceof Warehouse warehouse)
            wList = warehouse;
    }
*/
}