package manager;

import entity.AdminAccount;
import entity.Flight;
import entity.Staff;
import entity_list.StaffList;
import input.FlightInputUtil;
import interfaces.IActionListener;
import java.util.List;
import util.DataOperation;
import view.Menu;

public class Administrator implements IActionListener<FlightManager>{
    private static FlightManager flightM = null;
    private StaffManager staffM;
    private StaffList staffList;
    private AdminAccount admin;
    
    public Administrator (AdminAccount admin) {
        this.staffM = new StaffManager();
        this.staffList = staffM.getList();
        this.admin = admin;
    }
    
    //<editor-fold desc="ADMINISTRATOR FUNCTIONS" defaultstate="collapsed">
    public void administratorInterface () {
        while (true) {
            Menu.printMenu(Menu.ADMINISTRATOROPTIONS);
            int choice = DataOperation.readInt("Enter your choice");
            switch (choice) {
                case 1 -> {
                    if (flightM.getFlightsList().showAll())
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 2 -> {
                    if (flightM.addFlight()) 
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 3 -> {
                    if (flightM.updateFlight()) 
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 4 -> {
                    if (flightM.removeFlight()) 
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 5 -> {
                    staffManagementInterface();
                } case 6 -> {
                    if (admin.updateAdminAccount()) 
                        Menu.printStatement(Menu.SUCCESSFULSTATE);
                    else 
                        Menu.printStatement(Menu.FAILSTATE);
                } case 7 -> {
                    return;
                } default -> {
                    Menu.printStatement(Menu.INVALIDCHOICE);
                    administratorInterface();
                }
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="STAFF MANAGEMENT FUNCTIONS" defaultstate="collapsed">
    public void staffManagementInterface () {
        Menu.printMenu(Menu.STAFFMANAGEMENTOPTIONS);
        int choice = DataOperation.readInt("Enter your choice");
        switch (choice) {
            case 1 -> {
                if (staffM.addStaff())
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            } case 2 -> {
                if (staffM.updateStaff()) 
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            } case 3 -> {
                if (staffM.removeStaff()) 
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            } case 4 -> {
                if (staffM.getList().showAll()) 
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            } case 5 -> {
                assignStaffInterface();
            } case 6 -> {
                return;
            } default -> {
                Menu.printStatement(Menu.INVALIDCHOICE);
                assignStaffInterface();
            }
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="STAFF ASSIGNMENT FUNCTIONS" defaultstate="collapsed">
    public void assignStaffInterface () {
        String flightNum = FlightInputUtil.enterFlightNum("Enter the flight number", false, flightM.getFlightsList());
        Flight assignedFlight = flightM.getFlightsList().getByFlightNum(flightNum);
        
        staffList.showByFlight(assignedFlight);
        Menu.printMenu(Menu.ASSIGNSTAFFOPTIONS);
        int choice = DataOperation.readInt("Enter your choice");
        switch (choice) {
            case 1 -> {
                if (assignStaff(assignedFlight))
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            } case 2 -> {
                if (removeStaff(assignedFlight)) 
                    Menu.printStatement(Menu.SUCCESSFULSTATE);
                else 
                    Menu.printStatement(Menu.FAILSTATE);
            } case 3 -> {
                return;
            } default -> {
                Menu.printStatement(Menu.INVALIDCHOICE);
                assignStaffInterface();
            }
        }
    }
    
    public boolean assignStaff (Flight assignedFlight) {
        System.out.println("\t\tALL STAFF");
        staffList.showAll(assignedFlight.getAssignedStaff());
        int staffChoice = DataOperation.readInt("Enter assigning staff number");
        assignedFlight.getAssignedStaff().add(staffList.get(staffChoice - 1));
        return true;
    }
    
    public boolean removeStaff (Flight removedFlight) {
        int staffChoice = DataOperation.readInt("Enter removing staff number");
        List<Staff> staffs = removedFlight.getAssignedStaff();
        
        Menu.printStatement(Menu.CONTINUEMESSAGE);
        if (DataOperation.readChoice()) {
            staffs.remove(staffs.get(staffChoice - 1));
            return true;
        } return false;
    }
    //</editor-fold>
    
    @Override
    public void update(FlightManager o) {
        flightM = o;
    }
}