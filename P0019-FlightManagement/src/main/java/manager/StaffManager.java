package manager;

import entity.Staff;
import entity_list.StaffList;
import util.DataOperation;

public class StaffManager {
    private StaffList list;

    public StaffManager() {
        this.list = new StaffList();
    }
    
    public StaffManager (StaffList list) {
        this.list = list;
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public StaffList getList() {
        return list;
    }

    public void setList(StaffList list) {
        this.list = list;
    }
    //</editor-fold>
    
    //<editor-fold desc="STAFF LIST CRUD OPERATION WITH USER INPUT" defaultstate="collapsed">
    public boolean addStaff () {
        Staff newStaff = new Staff();
        newStaff.input();
        return list.addNew(newStaff);
    }
    
    public boolean updateStaff () {
        list.showAll();
        int updateStaffChoice = DataOperation.readInt("Enter chosen staff number");
        Staff updateStaff = list.get(updateStaffChoice - 1);
        return list.updating(updateStaff);
    }
    
    public boolean removeStaff () {
        list.showAll();
        int removeStaffChoice = DataOperation.readInt("Enter chosen staff number");
        Staff removeStaff = list.get(removeStaffChoice - 1);
        return list.deleting(removeStaff);
    }
    //</editor-fold>
}
