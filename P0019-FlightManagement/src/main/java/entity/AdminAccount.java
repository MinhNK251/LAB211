package entity;

import input.AccountInputUtil;
import java.util.ArrayList;
import java.util.Arrays;
import util.FileManager;

public class AdminAccount {
    private Account admin;
    
    public AdminAccount() {
        loadFileData();
    }
    
    private void loadFileData () {
        FileManager f = new FileManager("admin");
        while (f.nextLine() != null) {
            admin = new Account(f.nextVar(), f.nextVar());
        }
    }
    
    //<editor-fold desc="GETTERS AND SETTERS" defaultstate="collapsed">
    public Account getAdmin() {
        return admin;
    }

    public void setAdmin(Account admin) {
        this.admin = admin;
    }
    //</editor-fold>
    
    public boolean updateAdminAccount () {
        admin.setUsername(AccountInputUtil.enterUsername("Enter new username"));
        String password = AccountInputUtil.enterPassword("Enter new password");
        AccountInputUtil.reEnterPassword("Re-enter new password", password);
        admin.setPassword(password);
        FileManager.saveFile("admin", new ArrayList(Arrays.asList(admin.toString())));
        return true;
    }
}
