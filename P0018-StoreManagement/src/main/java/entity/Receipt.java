package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import interfaces.IProductListFunc;
import interfaces.IConstValue;
import util.DataOperation;
import view.Menu;
import util.Event;
import util.ProductInfoOperations;
import validator.CheckAvailable;

/*--------------------------------------------------------------------------------------------------
---Receipt abstract class that contain the basic add/remove/update/printAll function for products---
------------Two subclasses of Receipt abstract class are ImportReceipt and ExportReceipt------------
--------------------------------------------------------------------------------------------------*/

public abstract class Receipt implements IConstValue, IProductListFunc{
    protected final Event onListChange = new Event();
    
    static protected int incrementCode = 0;
    
    protected int code;
    protected String receiptType;
    protected LocalDateTime createdTime;
    protected int numberofProduct;
    protected List<Product> productList;
    
    public Receipt() {
        this.code = ++incrementCode;
        this.createdTime = LocalDateTime.now();
        this.numberofProduct = 0;
        this.productList = new ArrayList();
        this.onListChange.addListener(new CheckAvailable());
        this.onListChange.addListener(new ProductInfoOperations());
    }
    
    public Receipt (int code, String created) {
        ++incrementCode;
        this.code = code;
        this.setCreatedTime(created);
        this.productList = new ArrayList();
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public int getCode() {
        return code;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public String getCreatedTime() {
        return createdTime.format(TIMEPATTERN);
    }
    
    public int getNumberOfProduct () {
        return numberofProduct;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = LocalDateTime.parse(createdTime, TIMEPATTERN);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> itemList) {
        this.productList = itemList;
    }
    //</editor-fold>
    
    @Override
    public abstract Boolean addProduct ();
    
    public Boolean addProduct (Product newProduct) {
        productList.add(newProduct);
        numberofProduct++;
        return true;
    }
    
    @Override
    public Boolean deleteProduct () {
        onListChange.invoke(productList);
        
        String input = ProductInfoOperations.enterCode("Enter the product code", false);
        System.out.println(Menu.CONTINUEMESSAGE);
        if (DataOperation.readChoice()) {
            int removingIndex = ProductInfoOperations.searchByCode(input);
            productList.remove(productList.get(removingIndex));
            numberofProduct--;
        }
        return true;
    }

    @Override
    public abstract Boolean updateProduct();

    @Override
    public Boolean showProducts() {
        int counter = 0;
        for (Product product: productList) {
            System.out.printf("%s.\t%s\n", ++counter, product.toString());
        }
        return true;
    }
    
    public abstract Boolean confirmReceipt(ProductList pList);
        
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s_%07d_%s_%s",  getReceiptType(), getCode(), getCreatedTime(), getNumberOfProduct()));
        for (Product p: productList) {
            result.append("_").append(p.toString());
        }
        return result.toString();
    }
}