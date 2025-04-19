package entity;

import interfaces.IActionListener;
import interfaces.IConstValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.ProductInfoOperations;

/*-------------------------------------------------------------------------------------------
----Report class hold static methods for search for products that matches the requirement----
-------------------------------------------------------------------------------------------*/

public class Report implements IConstValue, IActionListener{
    //The privately stored productList and warehouse used for methods
    //The Object is renew whenever the subscribed event are fired
    private static ProductList pList;
    private static Warehouse wList;
    
    //Return a list of expired products
    //The requirements are manufactured date is before expired date and expired date is before today
    public static List<Product> getExpiredProducts() {
        List<Product> expiredProducts = new ArrayList();
        
        for (var product: pList) {
            if (LocalDate.parse(product.getExpirationD(), DATEPATTERN).isBefore(LocalDate.now())
                    && LocalDate.parse(product.getManufacturedD(), DATEPATTERN).compareTo(LocalDate.parse(product.getExpirationD(), DATEPATTERN)) <= 0) {
                expiredProducts.add(product);
            }
        }
        return expiredProducts;
    }

    //Return a list of currently selling products
    //The requirements are manufactured date is before expired date, the expired date is after the current date, and the product quantity larger than 0
    public static List<Product> getSellingProducts() {
        List<Product> sellingProducts = new ArrayList();
        
        for (var product: pList) {
            if (LocalDate.parse(product.getExpirationD(), DATEPATTERN).isAfter(LocalDate.now())
                    && LocalDate.parse(product.getManufacturedD(), DATEPATTERN).compareTo(LocalDate.parse(product.getExpirationD(), DATEPATTERN)) <= 0
                    && product.getQuantity() > 0) {
                sellingProducts.add(product);
            }
        }
        return sellingProducts;
    }
    
    //Return a list of product are going to run out of stock
    //The requirements are manufactured date is before expired date and the product quantity is lower than 10
    public static List<Product> getOutOfStockProducts() {
        List<Product> outOfStockProducts = new ArrayList();
        
        for (var product: pList) {
            if (LocalDate.parse(product.getManufacturedD(), DATEPATTERN).compareTo(LocalDate.parse(product.getExpirationD(), DATEPATTERN)) <= 0
                    && product.getQuantity() < 10) {
                outOfStockProducts.add(product);
            }
        }
        Collections.sort(outOfStockProducts, (Product p1, Product p2) -> 
            p1.getQuantity() - p2.getQuantity()
        );
        return outOfStockProducts;
    }
    
    //Return a list of receipts that contain the inputted product code in the product list
    public static List<Receipt> getProductReceipt () {
        List<Receipt> productReceipts = new ArrayList();
        
        String code = ProductInfoOperations.enterCode("Enter the product code", false);
        for (var receipt: wList) {
            for (var product: receipt.getProductList()) {
                if (product.getCode().equals(code)) {
                    productReceipts.add(receipt);
                    break;
                }
            }
        }
        return productReceipts;
    }
    
    //Print out the list that was passed in the function.
    //If it is empty, then print "There is no entry", otherwise, print the passed list
    public static Boolean printReport (List list) {
        if (list == null || list.isEmpty()) {
            System.out.println("There is no entry!");
            return true;
        }
        try {
            int counter = 0;
            for (var data: list) {
                System.out.printf("%d. %s.\n", ++counter, data.toString());
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void update(Object o) {
        if (o instanceof ProductList productList)
            pList = productList;
        else if (o instanceof Warehouse warehouse)
            wList = warehouse;
    }
}