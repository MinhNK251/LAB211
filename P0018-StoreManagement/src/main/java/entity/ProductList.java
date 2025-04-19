package entity;

import java.util.List;
import util.FileManager;
import util.DataOperation;
import view.Menu;
import interfaces.IProductListFunc;
import java.util.LinkedList;
import util.ProductInfoOperations;
import validator.CheckAvailable;
import util.Event;

/*-------------------------------------------------------------------------------------------------
----The Warehouse class is a linked list of Products with the methods for the basic methods for----
----------------------add/remove/update/show products and other related methods--------------------
-------------------------------------------------------------------------------------------------*/

public class ProductList extends LinkedList<Product> implements IProductListFunc{
    private final Event onListChange = new Event();
    
    public ProductList () {
        loadDataFromFile();
        onListChange.addListener(new CheckAvailable());
        onListChange.addListener(new ProductInfoOperations());
        onListChange.invoke(getList());
    }
    
    public List getList () {
        return this;
    }
    
    //Method for load file from product.dat file then add to the linked list
    private void loadDataFromFile () {
        FileManager f = new FileManager("product");
        while (f.nextLine() != null) {
            this.add(new Product(f.nextVar(), f.nextVar(), Integer.parseInt(f.nextVar()), f.nextVar(), f.nextVar()));
        }
        onListChange.invoke(this);
    }
    
    //Add a new product to the product list, after successfully adding, the new product is save directly to file
    @Override
    public Boolean addProduct () {
        onListChange.invoke(this);
        
        Product newProduct = new Product();
        newProduct.input();
        this.add(newProduct);
        FileManager.autoSaveFile("product", newProduct.toString());
        
        System.out.println(Menu.CONTINUEMESSAGE);
        if (DataOperation.readChoice())
            return addProduct();
        return true;
    }
    
    //Add a new product that was passed in the parameter
    public Boolean addProduct (Product newProduct) {
        this.add(newProduct);
        FileManager.autoSaveFile("product", newProduct.toString());
        return true;
    }
    
    //Delete product out of the product list, if the product list has already been listed in one of the receipt in warehouse
    //then print out error
    @Override
    public Boolean deleteProduct () {
        onListChange.invoke(this);
        
        String input;
        Boolean isInvalid;
        do {
            input = ProductInfoOperations.enterCode("Enter the product code", false);
            if (isInvalid = CheckAvailable.doesProductInReceipt(input)) System.out.println("The product is already in receipt, please try another!");
        } while (isInvalid);
        System.out.println(Menu.CONTINUEMESSAGE);
        if (DataOperation.readChoice()) 
            this.remove(this.get(ProductInfoOperations.searchByCode(input)));
        return true;
    }
    
    //Update product information based on the product code inputted
    @Override
    public Boolean updateProduct() {
        onListChange.invoke(this);

        String input = ProductInfoOperations.enterCode("Enter the product code", false);
        Product tempProduct = this.get(ProductInfoOperations.searchByCode(input));
        tempProduct.input(input);
        return true;
    }
    
    //Print all of the product currently in the product list
    @Override
    public Boolean showProducts() {
        int counter = 0;
        for (Product product: this) {
            System.out.printf("%s. \t%s\n", ++counter, product.toString());
        }
        return true;
    }
    
    //Return the product object found in the product list
    public Product getProductByCode (String code) {        
        for (Product temp: this) {
            if (temp.getCode().equals(code)) 
                return temp;
        }
        return null;
    }
}