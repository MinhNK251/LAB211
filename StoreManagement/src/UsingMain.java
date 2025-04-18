public class UsingMain {

    public static void main(String[] args) {

        int choice, choice1;
        ProductList list = new ProductList();
        boolean check;
        int count = 1;
        
        try {
            do {
                Utils.printMenu();
                choice = Utils.getInt("Enter your choice: ");
                switch (choice) {
                    case 1:
                        do {
                            System.out.println("\n----MANAGE PRODUCTS----");
                            System.out.println("1 - Add a product");
                            System.out.println("2 - Update product information");
                            System.out.println("3 - Delete product");
                            System.out.println("4 - Show all product");
                            System.out.println("Other - Back to main menu \n");
                            choice1 = Utils.getInt("Enter your choice: ");
                            switch (choice1) {
                                case 1: 
                                    list.add();
                                    count++;
                                    System.out.println("");
                                    break;

                                case 2: 
                                    list.update();
                                    count++;
                                    System.out.println("");
                                    break;
                                case 3:
                                    list.delete();
                                    count++;
                                    System.out.println("");
                                    break;
                                case 4:
                                    list.output();
                                    count++;
                                    System.out.println("");
                                    break;
                            }
                        } while (choice1 >= 1 && choice1 <= 4);
                        System.out.println("");
                        break;
                    case 2:
                        do {
                            System.out.println("\n----MANAGE WAREHOUSE----");
                            System.out.println("1 - Create an import receipt");
                            System.out.println("2 - Create an export receipt");
                            System.out.println("Other - Back to main menu \n");
                            choice1 = Utils.getInt("Enter your choice: ");
                            switch (choice1) {
                                case 1: 
                                    list.createReceipt("Import");
                                    count++;
                                    System.out.println("");
                                    break;

                                case 2: 
                                    list.createReceipt("Export");
                                    count++;
                                    System.out.println("");
                                    break;
                            }
                        } while (choice1 >= 1 && choice1 <= 2);
                        count++;
                        System.out.println("");
                        break;
                    case 3:
                        do {
                            System.out.println("\n----REPORT MENU----");
                            System.out.println("1 - Products that have expired");
                            System.out.println("2 - The products that the store is selling");
                            System.out.println("3 - Products that are running out of stock (sorted in ascending order)");
                            System.out.println("4 - Import/export receipt of a product");
                            System.out.println("Other - Back to main menu \n");
                            choice1 = Utils.getInt("Enter your choice: ");
                            switch (choice1) {
                                case 1: 
                                    list.expireList();
                                    System.out.println("");
                                    break;
                                case 2: 
                                    list.sellingList();
                                    System.out.println("");
                                    break;
                                case 3:
                                    list.outOfStock();
                                    System.out.println("");
                                    break;
                                case 4:
                                    list.importExport();
                                    System.out.println("");
                                    break;
                            }
                        } while (choice1 >= 1 && choice1 <= 4);
                        count++;
                        System.out.println("");
                        break;
                    case 4:
                        if(count!=1){
                            list.writeFile();
                            count = 1;
                        }
                        else System.err.println("List is empty, can't save!");
                        System.out.println("");
                        break;
                    case 5:
                        list.loadFile();
                        count++;
                        break;
                }
            } while (choice >= 1 && choice <= 5);
            
            if (choice >= 6 || choice <= -1) {
                if (count != 1) {
                    check = Utils.getYesNo("You have not saved! Do you want to save file(Yes/No): ");
                    if (check) {
                        list.writeFile();
                        System.out.println("\n---- End Program ----\n");
                    } else
                        System.out.println("\n---- End Program ----\n");
                } else
                    System.out.println("\n---- End Program ----\n");
                }
        } catch (Exception e) {
        }
    }
}