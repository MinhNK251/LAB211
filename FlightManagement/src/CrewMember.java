import java.io.Serializable;

public class CrewMember implements Serializable {
    private String name, id, role;

    public CrewMember() {
    }

    public CrewMember(String id) {
        this.id = id;
    }

    public CrewMember(String name, String id, String role) {
        this.name = name;
        this.id = id;
        this.role = role;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public void createCrewMember(){
        this.id = Utils.getString("ID: ");
        this.name = Utils.getString("Name: ");
        this.role = Utils.getRole("Role (pilot, flight attendant, and ground staff): ");
    }
    
    public void updateCrewMember(){
        this.id = Utils.updateString(this.name,"Update ID: ");
        this.name = Utils.updateString(this.name,"Update name: ");
        this.role = Utils.updateRole(this.role,"Update role (pilot, flight attendant, and ground staff): ");
    }
    
    @Override
    public String toString() {
        return "CrewMember ID - " + this.id + " || Name - " + this.name
                + " || Role - " + this.role;
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((CrewMember) obj).getId());
    }
}