import java.io.Serializable;
import java.util.Random;

public class Passenger implements Serializable {
    private String name, id, email;
    private int phone;

    public Passenger() {
    }

    public Passenger(String id) {
        this.id = id;
    }

    public Passenger(String name, String id, String email, int phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    protected void createPassenger() {
        this.name = Utils.getString("Name: ");
        this.email = Utils.getString("Email: ");
        this.phone = Utils.getInt("Phone: ");
    }

    protected void updatePassenger() {
        this.name = Utils.updateString(this.name,"Update name: ");
        this.email = Utils.updateString(this.email,"Update email: ");
        this.phone = Utils.updateInt(this.phone,"Update phone: ");
    }

    @Override
    public String toString() {
        return "Passenger ID - " + this.id + " || Name - " + this.name
                + " || Email - " + this.email + " || Phone - " + this.phone;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Passenger) obj).getId());
    }
}