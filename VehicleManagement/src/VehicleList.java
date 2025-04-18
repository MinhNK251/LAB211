import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VehicleList extends ArrayList<Vehicle> implements I_FunctionList {

    private ArrayList<Vehicle> list = new ArrayList<>();
    private Vehicle vh;
    private int index;

    public VehicleList() {
    }

    public VehicleList(Vehicle vh, int index) {
        this.vh = vh;
        this.index = index;
    }

    public ArrayList<Vehicle> getList() {
        return list;
    }

    public void setList(ArrayList<Vehicle> list) {
        this.list = list;
    }

    public Vehicle getVh() {
        return vh;
    }

    public void setVh(Vehicle vh) {
        this.vh = vh;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    public void add() {
        int choice;
        do {
            vh = new Vehicle();
            vh.createVehicle();
            index = list.indexOf(vh);
            if (index == -1) {
                list.add(vh);
                System.out.println("Vehicle added successfully!");
            } else
                System.out.println("Vehicle is dupplicated!");
            System.out.println();
        } while (Utils.getYesNo("Continue? (Yes/No): "));
    }
    
    public void checkIdExist() {
        loadFile();
        do {
            this.index = find(Utils.getString("Enter Vehicle ID that you want to check: "));
            if (index != -1) 
                System.out.println("Exist vehicle");
            else System.out.println("No vehicle found!");
        } while (Utils.getYesNo("Continue? (Yes/No): "));
    }

    @Override
    public int find(String code) {
        index = list.indexOf(new Vehicle(code));
        return index;
    }

    @Override
    public void delete() {
        this.index = find(Utils.getString("Enter Vehicle ID that you want to delete: "));
        boolean check;
        if (index != -1) {
            check = Utils.getYesNo("Are you sure you want to delete (Yes/No): ");
            if (check) {
                list.remove(index);
                System.out.println("Delete Success!");
            } else {
                System.out.println("Delete Fail!");
            }
        } else {
            System.out.println("No vehicle found!");
        }
    }

    @Override
    public void update() {
        this.index = find(Utils.getString("Enter the Vehicle ID that you want to update: "));
        if (index != -1) {
            vh = list.get(index);
            vh.updateVehicle();
            list.set(index, vh);
            System.out.println("Vehicle updated!");
            System.out.println("The result of the update: \n" + vh.toString());
        } else {
            System.out.println("No vehicle found!");
        }
    }

    @Override
    public void search() {
        int choice;
        String text;
        int count = 0;
        
        try {
            do {
                System.out.println("\n----SEARCH VEHICLE MENU----");
                System.out.println("1 - Searching by vehicle name");
                System.out.println("2 - Searching by vehicle id");
                System.out.println("Other - Back to main menu \n");
                choice = Utils.getInt("Enter your choice: ");
                switch (choice) {
                    case 1: // Search by vehicle Name.
                        text = Utils.getString("Enter the vehicle name you want to find: ");
                        for (Vehicle vh : list) {
                            if (vh.getName_Vehicle() != null && (vh.getName_Vehicle().contains(text))) {
                                System.out.println(vh.toString());
                                count ++;
                            }
                        }
                        if (count == 0)
                            System.out.println("No Vehicle found!\n");
                        else System.out.println("");
                        break;
                        
                    case 2: // Search by Vehicle ID.
                        text = Utils.getString("Enter the Vehicle ID you want to find: ");
                        for (Vehicle vh : list) {
                            if (vh.getID_Vehicle() != null && vh.getID_Vehicle().equalsIgnoreCase(text)) {
                                System.out.println(vh.toString());
                                count ++;
                                break;
                            }
                        }
                        if (count == 0)
                            System.out.println("No vehicle found!\n");
                        else System.out.println("");
                        break;
                }
            } while (choice >= 1 && choice <= 2);
        } catch (Exception e) {
        }
    }

    @Override
    public void output() {
        int choice;
        if (list.isEmpty()){
            System.out.println("List is empty!\n");
            return;
        }
        try {
            do {
                System.out.println("\n----SHOW VEHICLE MENU----");
                System.out.println("1 - Show all");
                System.out.println("2 - Show by type");
                System.out.println("3 - Show descending by price");
                System.out.println("Other - Back to menu\n");
                choice = Utils.getInt("Enter your choice: ");
                switch (choice) {
                    case 1: // Show all.
                        for (Vehicle vh : list) {
                            System.out.println(vh.toString());
                        }
                        System.out.println("");
                        break;
                        
                    case 2: //Show by type
                        String tmp = Utils.getType("Type (Sport/Travel/Common): ");
                        for (Vehicle vh : list)
                            if (vh.getType().equalsIgnoreCase(tmp))
                                System.out.println(vh.toString());
                        System.out.println("");
                        break;
                        
                    case 3: // Show descending by Price.
                        ArrayList<Vehicle> clonelist = (ArrayList<Vehicle>) list.clone();
                        Collections.sort(clonelist, new Comparator<Vehicle>() {
                            @Override
                            public int compare(Vehicle c1, Vehicle c2) {
                                if (c1.getPrice_Vehicle() < c2.getPrice_Vehicle())
                                    return 1;
                                else {
                                    if (c1.getPrice_Vehicle() == c2.getPrice_Vehicle())
                                        return 0;
                                    return -1;
                                }
                            }
                        });
                        for (Vehicle vh : clonelist) {
                            System.out.println(vh.toString());
                        }
                        System.out.println("");
                        break;
                }
            } while (choice >= 1 && choice <= 3);
        } catch (Exception e) {
        }
    }

    @Override
    public void readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("vehicle.dat");
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Vehicle>) ois.readObject();
            for (Vehicle vh : list) {
                System.out.println(vh.toString());
            }
            System.out.println("");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found!");
        } catch (IOException ex) {
            System.err.println("IOException!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found!");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.err.println("IOException!");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.err.println("IOException!");
                }
            }
        }
    }

    @Override
    public void writeFile() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("vehicle.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            System.err.println("File Saved!");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found!");
        } catch (IOException ex) {
            System.err.println("IOException!");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    System.err.println("IOException!");
                }
            }
        }
    }
    
    public void loadFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("vehicle.dat");
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Vehicle>) ois.readObject();
            System.out.println("vehicle.dat Loaded!");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found!");
        } catch (IOException ex) {
            System.err.println("IOException!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found!");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.err.println("IOException!");
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.err.println("IOException!");
                }
            }
        }
    }
}