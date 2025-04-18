import java.io.Serializable;

public class Vehicle implements Serializable  {

    private String ID_Vehicle, name_Vehicle, color_Vehicle, brand_Vehicle, type;
    private int price_Vehicle, productYear;

    public Vehicle() {
    }

    public Vehicle(String ID_Vehicle) {
        this.ID_Vehicle = ID_Vehicle;
    }

    public Vehicle(String ID_Vehicle, String name_Vehicle, String color_Vehicle, String brand_Vehicle, String type, int price_Vehicle, int productYear) {
        this.ID_Vehicle = ID_Vehicle;
        this.name_Vehicle = name_Vehicle;
        this.color_Vehicle = color_Vehicle;
        this.brand_Vehicle = brand_Vehicle;
        this.type = type;
        this.price_Vehicle = price_Vehicle;
        this.productYear = productYear;
    }

    public String getID_Vehicle() {
        return ID_Vehicle;
    }

    public void setID_Vehicle(String ID_Vehicle) {
        this.ID_Vehicle = ID_Vehicle;
    }

    public String getName_Vehicle() {
        return name_Vehicle;
    }

    public void setName_Vehicle(String name_Vehicle) {
        this.name_Vehicle = name_Vehicle;
    }

    public String getColor_Vehicle() {
        return color_Vehicle;
    }

    public void setColor_Vehicle(String color_Vehicle) {
        this.color_Vehicle = color_Vehicle;
    }

    public String getBrand_Vehicle() {
        return brand_Vehicle;
    }

    public void setBrand_Vehicle(String brand_Vehicle) {
        this.brand_Vehicle = brand_Vehicle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice_Vehicle() {
        return price_Vehicle;
    }

    public void setPrice_Vehicle(int price_Vehicle) {
        this.price_Vehicle = price_Vehicle;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }

    

   
    protected void createVehicle() {
        this.ID_Vehicle = Utils.getString("Vehicle ID: ");
        this.name_Vehicle = Utils.getString("Name: ");
        this.color_Vehicle = Utils.getColor("Color (Blue/Green/Red): ");
        this.price_Vehicle = Utils.getGreaterThan0("Price: ");
        this.brand_Vehicle = Utils.getString("Brand: ");
        this.type = Utils.getType("Type (Sport/Travel/Common): ");
        this.productYear = Utils.getYear("Year of manufacture: ");
    }

    protected void updateVehicle() {
        this.ID_Vehicle = Utils.updateString(this.ID_Vehicle, "Update Vehicle ID: ");
        this.name_Vehicle = Utils.updateString(this.name_Vehicle, "Update Vehicle Name: ");
        this.color_Vehicle = Utils.updateColor(this.color_Vehicle, "Update Vehicle Color (Blue/Green/Red): ");
        this.price_Vehicle = Utils.updateGreaterThan0(this.price_Vehicle, "Update Vehicle Price: ");
        this.brand_Vehicle = Utils.updateString(this.brand_Vehicle, "Update Vehicle Brand: ");
        this.type = Utils.updateType(this.type, "Update Vehicle Type (Sport/Travel/Common): ");
        this.productYear = Utils.updateYear(this.productYear, "Update Vehicle Year of manufacture: ");
    }

    @Override
    public String toString() {
        return "Vehicle ID - " + this.ID_Vehicle + " || Name - " + this.name_Vehicle
                + " || Color - " + this.color_Vehicle + " || Price - " + this.price_Vehicle
                + " || Brand - " + this.brand_Vehicle + " || Type - " + this.type + " || Product Year - " + this.productYear;
    }

    @Override
    public boolean equals(Object obj) {
        return this.ID_Vehicle.equals(((Vehicle) obj).getID_Vehicle());
    }
}