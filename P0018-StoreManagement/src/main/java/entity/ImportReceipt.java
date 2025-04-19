package entity;

import util.DataOperation;
import util.FileManager;
import util.ProductInfoOperations;
import validator.CheckAvailable;
import view.Menu;

public class ImportReceipt extends Receipt{
    public ImportReceipt() {
        super.receiptType = "import";
    }
    
    public ImportReceipt (int code, String created) {
        super(code, created);
        super.receiptType = "import";
    }
    
    //Add product into the receipt product list based on product code, if exist then only renew the date and the quantity importing
    //If the produce code is not available, there is a choice for add the new product into the main product list, otherwise, return back to menu
    @Override
    public Boolean addProduct() {
        String input = ProductInfoOperations.enterCode("Enter the product code", false);
        if (CheckAvailable.doesProductExist(input)) {
            addExistProduct(input);
        } else {
            System.out.println("Product does not exist, do you want to add it to the main product list? Y/N");
            if (DataOperation.readChoice()) addNewProduct();
            else return true;
        }
        numberofProduct++;
        System.out.println(Menu.CONTINUEMESSAGE);
        if (DataOperation.readChoice())
            return addProduct();
        return true;
    }
    
    private void addExistProduct (String code) {
        Product existProduct = new Product(CheckAvailable.getList().get(ProductInfoOperations.searchByCode(code)));
        existProduct.input(code);
        productList.add(existProduct);
    }
    
    private void addNewProduct () {
        Product newProduct = new Product();
        newProduct.input();
        productList.add(newProduct);
    }
    
    //Update product information in the current receipt product list
    @Override
    public Boolean updateProduct() {
        onListChange.invoke(productList);
 
        String input = ProductInfoOperations.enterCode("Enter the product code", false);
        int updatingIndex = ProductInfoOperations.searchByCode(input);
        productList.get(updatingIndex).input(input);
        return true;
    }

    @Override
    public Boolean confirmReceipt(ProductList pList) {
        for (int i = 0; i < getNumberOfProduct(); i++) {
            Product currentP = getProductList().get(i);
            if (CheckAvailable.doesProductExist(currentP.getCode())) {
                Product updatedProduct = pList.get(ProductInfoOperations.searchByCode(currentP.getCode()));
                
                updatedProduct.setQuantity(currentP.getQuantity() + updatedProduct.getQuantity());
                updatedProduct.setExpirationD(currentP.getExpirationD());
                updatedProduct.setManufacturedD(currentP.getManufacturedD());                
            } else {
                pList.addProduct(currentP);
            }
        }
        FileManager.autoSaveFile("warehouse", toString());
        FileManager.saveFile("product", pList);    
        return true;
    }
}