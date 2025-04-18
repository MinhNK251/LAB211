import java.io.Serializable;

public class Product implements Serializable{

    protected String productCode, productName, manufacturingDate, expirationDate;
    protected int price, quantity;

    public Product() {
    }

    public Product(String productCode) {
         this.productCode = productCode;
    }
    
    public Product(String productCode, String productName, String manufacturingDate, String expirationDate, int price, int quantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void createProduct() {
        this.productCode = Utils.getString("Product code: ");
        this.productName = Utils.getString("Product name: ");
        this.manufacturingDate = Utils.getDate("Manufacturing date(dd/MM/yyyy): ");
        this.expirationDate = Utils.getDate("Expiration Date(dd/MM/yyyy): ");
        this.price= Utils.getNotNegative("Product Price: ");
        this.quantity = Utils.getNotNegative("Quantity: ");
    }

    public void updateProduct() {
        this.productCode = Utils.updateString(this.productCode, "Update product code: ");
        this.productName = Utils.updateString(this.productName, "Update product name: ");
        this.manufacturingDate = Utils.updateDate( this.manufacturingDate, "Update manufacturing date(dd/MM/yyyy): ");
        this.expirationDate = Utils.updateDate(this.expirationDate, "Update expiration Date(dd/MM/yyyy): ");
        this.price= Utils.updateNotNegative(this.price, "Update product Price: ");
        this.quantity = Utils.updateNotNegative(this.quantity, "Update quantity: ");
    }

    @Override
    public String toString() {
        return "Product code - " + this.productCode + " || Product Name - " + this.productName
                + " || Manufacturing date - " + manufacturingDate + " || Expiration date - " + this.expirationDate
                + " || Price - " + this.price + " Dong || Quantity - " + this.quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return this.productCode.equals(((Product) obj).getProductCode());
    }
}