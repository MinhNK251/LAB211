package manager;

import entity.Account;
import entity.AdminAccount;
import entity.Passenger;
import entity_list.AccountsList;
import exception.InvalidInputException;
import input.AccountInputUtil;
import util.DataOperation;
import util.FileManager;
import validator.CheckAvailable;
import validator.CheckValid;
import view.Menu;

public final class LogInManager{
    private AccountsList accountList;
    private Account currentAccount;
    private ReserveManager reserveM = null;
    private Administrator adminManager = null;
    
    public LogInManager (boolean adminLogIn) {
        if (adminLogIn) {
            AdminAccount admin = new AdminAccount();
            logInAdministrator(admin.getAdmin());
            adminManager = new Administrator(admin);
        } else {
            this.accountList = new AccountsList();
            this.currentAccount = null;
            logInInterface();
            if (currentAccount != null)
                reserveM = new ReserveManager(getAccount().getPassengerInfo());
        }
    }
    
    //<editor-fold desc="GETTERS" defaultstate="collapsed">
    public Account getAccount() {
        return currentAccount;
    }
    
    public ReserveManager getReserveManager () {
        return reserveM;
    }
    
    public Administrator getAdministrator () {
        return adminManager;
    }
    //</editor-fold>
    
    public void logInInterface () {
        Menu.printMenu(Menu.LOGINOPTIONS);
        int choice = DataOperation.readInt("Enter your choice");
        switch (choice) {
            case 1 -> {
                if (logInPassenger()) {
                    Menu.printStatement(Menu.SUCCESSFULLOGIN);
                    return;
                } else
                    Menu.printStatement(Menu.FAILSTATE);
            }
            case 2 -> {
                register();
                Menu.printStatement(Menu.SUCCESSFULREGISTER);
                logInInterface();
            }
            case 3 -> {
                return;
            }
            default -> {
                System.out.println("Invalid user choice, please try again!");
                logInInterface();
            }
        }
    }
    
    public boolean logInAdministrator (Account adminAcc) {
        while (true) {
            try {
                String username = AccountInputUtil.enterUsername("Enter admin username");
                if (!username.equals(adminAcc.getUsername())) 
                    throw new Exception ("Invalid username, please try again");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        logInPassword(adminAcc);
        return true;
    }
    
    public boolean logInPassenger () {
        Account tempAccount = logInUsername();
        logInPassword(tempAccount);
        currentAccount = tempAccount;
        return true;
    }
    
    private Account logInUsername () {
        do {
            try {
                return CheckAvailable.doesAccountExist( 
                        AccountInputUtil.enterUsername("Enter your username"), accountList
                );
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    
    private void logInPassword (Account enteredAccount) {
        do {
            try {
                String password = AccountInputUtil.enterPassword("Enter your password");
                CheckValid.isPasswordCorrect(enteredAccount, password);
                return;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    
    public boolean register () {
        try {
            String username = AccountInputUtil.enterUsername("Enter your new username");
            String password = AccountInputUtil.enterPassword("Enter your new password");
            AccountInputUtil.reEnterPassword("Re-enter your new password", password);

            Passenger newPassenger = new Passenger();
            newPassenger.input();
            currentAccount = new Account(username, password, newPassenger);
            accountList.add(currentAccount);
            FileManager.autoSaveFile("account", currentAccount.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
