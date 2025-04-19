package entity;

import java.util.LinkedList;
import util.FileManager;

/*-------------------------------------------------------------------------------------------
----The Warehouse class is a linked list of Receipt with the methods for creating receipt----
----------------------------and get the currently working receipt----------------------------
-------------------------------------------------------------------------------------------*/

public class Warehouse extends LinkedList<Receipt>{
    public Warehouse () {
        loadDataFromFile();
    }
    
    //The function create an instance FileManager with warehouse filename
    //It create new Receipt objects based on the receipt type specify head of the line
    private void loadDataFromFile () {
        FileManager f = new FileManager("warehouse");
        while (f.nextLine() != null) {
            Receipt newReceipt;
            if (f.nextVar().equalsIgnoreCase("IMPORT"))
                newReceipt = new ImportReceipt(Integer.parseInt(f.nextVar()), f.nextVar());
            else 
                newReceipt = new ExportReceipt(Integer.parseInt(f.nextVar()), f.nextVar());
            
            int numberOfProduct = Integer.parseInt(f.nextVar());
            for (int i = 0; i < numberOfProduct; i++) {
                newReceipt.addProduct(
                        new Product(f.nextVar(), f.nextVar(), Integer.parseInt(f.nextVar()), f.nextVar(), f.nextVar())
                );
                f.nextVar();
            }
            this.add(newReceipt);
        }
    }
    
    public Boolean createImport () {
        this.add(new ImportReceipt());
        return true;
    }
    
    public Boolean createExport () {
        this.add(new ExportReceipt());
        return true;
    }
    
    public Boolean showReceipt () {
        int counter = 0;
        try {
            for (var receipt: this) {
                System.out.printf("%s.\t%s.\n", ++counter, receipt.toString());
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public Receipt getLatestReceipt () {
        return this.getLast();
    }
}