package entity_list;

import entity.Account;
import entity.Passenger;
import java.util.LinkedList;
import util.FileManager;

public class AccountsList extends LinkedList<Account>{
    public AccountsList () {
        loadFileData();
    }
    
    private void loadFileData () {
        FileManager f = new FileManager("account");
        try {
            while (f.nextLine() != null) {
                this.add(new Account(f.nextVar(), f.nextVar(), 
                        new Passenger(f.nextVar(), f.nextVar())
                ));
            }
        } catch (NumberFormatException e) {
            System.out.println("Fail to load file data, check contact info!");
        }
    }
    
    public void printAllAccounts () {
        int counter = 0;
        for (Account a: this) {
            System.out.printf("%d. %n\n", ++counter, a.toString());
        }
    }
}
