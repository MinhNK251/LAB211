package entity_list;

import entity.Flight;
import entity.Staff;
import interfaces.ICRUDOperations;
import java.util.LinkedList;
import java.util.List;
import util.FileManager;

public class StaffList extends LinkedList<Staff> implements ICRUDOperations<Staff>{
    
    public StaffList () {
        loadFileData();
    }
    
    private void loadFileData () {
        FileManager f = new FileManager("staff");
        while (f.nextLine() != null) {
            this.add(new Staff(f.nextVar(), Integer.parseInt(f.nextVar()), f.nextVar()));
        }
    }
    
    @Override
    public Boolean addNew(Staff newElement) {
        if (newElement == null || newElement.isEmpty())
            return false;
        this.add(newElement);
        FileManager.autoSaveFile("staff", newElement.toString());
        return true;
    }

    @Override
    public Boolean updating(Staff updateElement) {
        updateElement.input();
        FileManager.saveFile("staff", this);
        return true;
    }

    @Override
    public Boolean deleting(Staff removeElement) {
        if (removeElement == null || removeElement.isEmpty())
            return false;
        this.remove(removeElement);
        FileManager.saveFile("staff", this);
        return true;
    }

    @Override
    public Boolean showAll() {
        int counter = 0;
        for (var staff: this) {
            System.out.printf("%d. %s.\n", ++counter, staff.toString());
        }
        return true;
    }
    
    public boolean showAll (List<Staff> constrants) {
        int counter = 0; 
        for (var staff: this) {
            if (!constrants.contains(staff))
                System.out.printf("%d. %s\n", ++counter, staff.toString());
        }
        return true;
    }
    
    public boolean showByFlight (Flight currentF) {
        int counter = 0;
        System.out.printf("\t\tFLIGHT %s STAFFFS\n", currentF.getFlightNum());
        for (var staff: currentF.getAssignedStaff()) {
            System.out.printf("%d. %s.\n", ++counter, staff.toString());
        }
        return true;
    }
}
