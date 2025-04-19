package entity;

import input.StaffInputUtil;
import util.ConstVars;
import util.DataOperation;

public class Staff {
    private ConstVars consts = ConstVars.getInstance();
    
    private String name;
    private int age;
    private String role;

    public Staff () {
    }
    
    public Staff(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = checkRole(role);
    }

    public Staff(String name, int age, int roleNum) {
        this.name = name;
        this.age = age;
        this.role = checkRole(roleNum);
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s_%s_%s", getName(), getAge(), getRole());
    }
    
    public boolean isEmpty () {
        return (getName().isBlank()) && (getAge() <= 0) && (getRole().isBlank());
    }
    
    public void input () {
        String staffName = StaffInputUtil.enterStaffName("Enter new staff name");
        int staffAge = StaffInputUtil.enterStaffAge("Enter new staff age");
        String staffRole = StaffInputUtil.enterStaffRole();
        
        this.setName(staffName);
        this.setAge(staffAge);
        this.setRole(staffRole);
    }

    @Override
    public boolean equals(Object obj) {
        Staff otherS = (Staff)obj;
        return getName().equals(otherS.getName()) && getRole().equals(otherS.getRole()) && getAge() == otherS.getAge();
    }
    
    //<editor-fold desc="ROLE CHECK" defaultstate="collapsed">
    private String checkRole (String checkR) {
        for (String role: consts.STAFFROLE) {
            if (role.equalsIgnoreCase(checkR)) 
                return DataOperation.firstLetterUpper(role);
        }
        return null;
    }
    
    private String checkRole (int roleIndex) {
        return (roleIndex < consts.STAFFROLE.length)? DataOperation.firstLetterUpper(consts.STAFFROLE[roleIndex]): null;
    }
    //</editor-fold>
}
