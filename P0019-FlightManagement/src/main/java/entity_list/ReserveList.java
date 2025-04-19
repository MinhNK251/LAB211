package entity_list;

import entity.Passenger;
import entity.Reservation;
import interfaces.ICRUDOperations;
import java.util.LinkedList;
import java.util.List;
import util.FileManager;

public class ReserveList extends LinkedList<Reservation> implements ICRUDOperations<Reservation>{
    public ReserveList () {
        loadData();
    }
    
    private void loadData () {
        FileManager f = new FileManager("reservation");
        while (f.nextLine() != null) {
            this.add(new Reservation(f.nextVar(), f.nextVar(), f.nextVar(), f.nextVar(), f.nextVar()));
        }
    }
    
    @Override
    public Boolean addNew(Reservation newReserve) {
        if (newReserve == null || newReserve.isEmpty())
            return false;
        this.add(newReserve);
        FileManager.autoSaveFile("reservation", newReserve.toString());
        return true;
    }

    @Override
    public Boolean deleting(Reservation removeReserve) {
        if (removeReserve == null || removeReserve.isEmpty())
            return false;
        this.remove(removeReserve);
        FileManager.saveFile("reservation", this);
        return true;
    }

    @Override
    public Boolean updating(Reservation updateElement) {
        return ICRUDOperations.super.updating(updateElement); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Boolean showAll() {
        int counter = 0;
        for (Reservation r: this) {
            System.out.printf("%d. %s\n", ++counter, r.toString());
        }
        return true;
    }
    
    public Reservation getByID (String reserveID) {
        for (var reserve: this) {
            if (reserve.getReserveID().equalsIgnoreCase(reserveID))
                return reserve;
        }
        return null;
    }
            
    public List<Reservation> getAllSpecificReserve (Passenger searchPassenger) {
        List<Reservation> reserveList = new LinkedList<>();
        for (var reserve: this) {
            if (reserve.getPassenger().equals(searchPassenger))
                reserveList.add(reserve);
        }
        return reserveList;
    }
    
    public void showAllSpecificReserve (Passenger searchPassenger) {
        List<Reservation> list = getAllSpecificReserve(searchPassenger);
        if (list.isEmpty()) {
            System.out.println("There is no entry");
            return;
        }
        int counter = 0;
        for (var reserve: list) {
            System.out.printf("%d. %s\n", ++counter, reserve.toString());
        }
    }
    
    public static void main(String[] args) {
        ReserveList test = new ReserveList();
        test.addNew(new Reservation("Nguyen Hoang Dung", "0916320563", "F0001"));
        test.addNew(new Reservation("Nguyen Thi Thuy Trang", "0919853328", "F0002"));
        test.showAll();
        FileManager.saveFile("reservation", test);
    }
}
