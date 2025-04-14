public class Motorbike {
    private String licensePlate;
    private String owner;
    private String brand;
    private long value;

    public Motorbike() {
    }

    public Motorbike(String licensePlate, String owner, String brand, long value) {
        this.licensePlate = "";
        this.owner = "";
        this.brand = "";
        this.value = value;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwner() {
        return owner;
    }

    public String getBrand() {
        return brand;
    }

    public long getValue() {
        return value;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setValue(long value) {
        this.value = value;
    }
    
    
}
