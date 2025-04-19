package entity;

import util.DataOperation;

public final class Passenger{
    private String name;
    private long contactNum;

    public Passenger(String name, long contactNum) {
        this.name = name;
        this.contactNum = contactNum;
    }
    
    public Passenger (String name, String contactNum) {
        this.name = name;
        this.setContactNum(contactNum);
    }

    public Passenger() {
        this.name = null;
        this.contactNum = 0;
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNum() {
        return String.format("%10s", contactNum).replaceAll(" ", "0");
    }

    public void setContactNum(String contactNum) {
        this.contactNum = Long.parseLong(contactNum);
    }
    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s_%s", getName(), getContactNum());
    }

    @Override
    public boolean equals(Object obj) {
        Passenger otherP = (Passenger)obj;
        return (this.name.equals(otherP.getName())) && (this.getContactNum().equals(otherP.getContactNum()));
    }
    
    public void input () {
        String newName = DataOperation.readString("Enter passenger name");
        String contact = DataOperation.readString("Enter passenger contact number");
        
        setName(newName);
        setContactNum(contact);
    }
}