package entity;

import java.time.LocalDate;
import util.ProductInfoOperations;
import interfaces.IConstValue;

public class Product implements IConstValue{    
    private String code;
    private String name;
    private int quantity;
    private LocalDate manufacturedD;
    private LocalDate expirationD;
    private String type;
    
    public Product () {   
    }
    
    public Product(String _code, String _name, int _quantity, String _manufacturedD, String _expirationD) {
        this.code = _code;
        this.name = _name;
        this.quantity = _quantity;
        this.manufacturedD = LocalDate.parse(_manufacturedD, DATEPATTERN);
        this.expirationD = LocalDate.parse(_expirationD, DATEPATTERN);
        this.type = ProductInfoOperations.calculateType(this);
    }
    
    //Product Constructor for the deep copy of an original Product object
    public Product (Product original) {
        this.code = original.getCode();
        this.name = original.getName();
        this.quantity = original.getQuantity();
        this.setManufacturedD(original.getManufacturedD());
        this.setExpirationD(original.getExpirationD());
        this.type = original.getType();
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getManufacturedD() {
        return manufacturedD.format(DATEPATTERN);
    }

    public void setManufacturedD(String manufacturedD) {
        this.manufacturedD = LocalDate.parse(manufacturedD, DATEPATTERN);
    }

    public String getExpirationD() {
        return expirationD.format(DATEPATTERN);
    }

    public void setExpirationD(String expirationD) {
        this.expirationD = LocalDate.parse(expirationD, DATEPATTERN);
    }
    
    public void setType (String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s_%s_%s_%s_%s_%s", 
                getCode(), getName(), getQuantity(), getManufacturedD(), getExpirationD(), getType());
    }
    
    //Input method for enter all new information
    public void input (){
        String codeInput = ProductInfoOperations.enterCode("Enter the product code", true),
               nameInput = ProductInfoOperations.enterName("Enter the product name"),
               manufactureInput = ProductInfoOperations.enterDate("Enter the manufactured date (dd-mm-yyyy)"),
               expireInput = ProductInfoOperations.enterDate("Enter the expiration date (dd-mm-yyyy)");
        int quantityInput = ProductInfoOperations.enterQuantity("Enter the product quantity");

        confirmInput(codeInput, nameInput, quantityInput, manufactureInput, expireInput);
    }
    
    //Input method for enter new information for an existent product in the product list
    public void input (String code) {
        String manufactureInput = ProductInfoOperations.enterDate("Enter the manufactured date (dd-mm-yyyy)"),
               expireInput = ProductInfoOperations.enterDate("Enter the expiration date (dd-mm-yyyy)");
        int quantityInput = ProductInfoOperations.enterQuantity("Enter the product quantity");

        confirmInput(code, this.getName(), quantityInput, manufactureInput, expireInput);
    }
    
    //Method for confirm the input in the Input function and save into fields
    private void confirmInput (String code, String name, int quantity, String manufacture, String expire) {
        setCode(code);
        setName(name);
        setQuantity(quantity);
        setManufacturedD(manufacture);
        setExpirationD(expire);
        setType(ProductInfoOperations.calculateType(this));
    }
}