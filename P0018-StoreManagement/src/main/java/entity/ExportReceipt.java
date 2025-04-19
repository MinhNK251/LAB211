package entity;

import util.DataOperation;
import util.FileManager;
import util.ProductInfoOperations;
import validator.CheckAvailable;
import view.Menu;

public class ExportReceipt extends Receipt{
    public ExportReceipt() {
        super.receiptType = "export";
    }
    
    public ExportReceipt (int code, String created) {
        super(code, created);
        super.receiptType = "export";
    }
    
    //Add product into the receipt product list based on exist product in the product list
    //Products are added by entering product code and specify its quantity (not exceeding current quantity) for exporting
    @Override
    public Boolean addProduct() {
        String inputCode = ProductInfoOperations.enterCode("Enter the product code", false);
        Product existedProduct = CheckAvailable.getList().get(ProductInfoOperations.searchByCode(inputCode));
        Product exportProduct = new Product(existedProduct);
        
        int quantity;
        do {
            quantity = ProductInfoOperations.enterQuantity("Enter the product quantity");
            if (quantity > existedProduct.getQuantity()) 
                System.out.println("The export quantity exceed the product's current quantity!");
        } while (quantity > existedProduct.getQuantity());
        exportProduct.setQuantity(quantity);
        productList.add(exportProduct);
        
        numberofProduct++;
        System.out.println(Menu.CONTINUEMESSAGE);
        if (DataOperation.readChoice())
            return addProduct();
        return true;
    }
    
    //Update product quantity information in the current receipt product list
    @Override
    public Boolean updateProduct() {
        onListChange.invoke(productList);
 
        String inputCode = ProductInfoOperations.enterCode("Enter the product code", false);
        int updatingIndex = ProductInfoOperations.searchByCode(inputCode);
        Product existedProduct = CheckAvailable.getList().get(ProductInfoOperations.searchByCode(inputCode));
        
        int quantity;
        do {
            quantity = ProductInfoOperations.enterQuantity("Enter the product quantity");
            if (quantity > existedProduct.getQuantity()) System.out.println("The export quantity exceed the product's current quantity!");
        } while (quantity > existedProduct.getQuantity());
        productList.get(updatingIndex).setQuantity(quantity);
        return true;
    }

    @Override
    public Boolean confirmReceipt(ProductList pList) {
        for (int i = 0; i < getNumberOfProduct(); i++) {
            Product currentP = getProductList().get(i);
            Product updatedProduct = pList.getProductByCode(currentP.getCode());
            updatedProduct.setQuantity(updatedProduct.getQuantity() - currentP.getQuantity());
        }
        FileManager.autoSaveFile("warehouse", toString());
        FileManager.saveFile("product", pList);
        return true;
    }
}