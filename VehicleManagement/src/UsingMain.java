public class UsingMain {

    public static void main(String[] args) {

        int choice;
        VehicleList list = new VehicleList();
        boolean check;
        int count = 1;
        
        try {
            do {
                Utils.printMenu();
                choice = Utils.getInt("Enter your choice: ");
                switch (choice) {
                    case 0:
                        list.add();
                        count++;
                        System.out.println("");
                        break;
                    case 1:
                        list.checkIdExist();
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
                        list.search();
                        System.out.println("");
                        break;
                    case 5:
                        list.output();
                        System.out.println("");
                        break;
                    case 6:
                        list.writeFile();
                        count = 1;
                        System.out.println("");
                        break;
                    case 7:
                        list.readFile();
                        break;
                    case 8:
                        list.loadFile();
                        break;
                }
            } while (choice >= 0 && choice <= 8);
            
            if (choice >= 9 || choice <= -1) {
                if (count != 1) {
                    check = Utils.getYesNo("You have not saved! Do you want to save it to a file (Yes/No): ");
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