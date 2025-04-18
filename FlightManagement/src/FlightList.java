import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FlightList extends ArrayList<Flight> implements I_FunctionList {

    private ArrayList<Flight> list = new ArrayList<>();
    private ArrayList<Passenger> plist = new ArrayList<>();
    private ArrayList<CrewMember> clist = new ArrayList<>();
    private Flight f;
    private Passenger p;
    private CrewMember c;
    private int index;

    public FlightList() {
    }

    public FlightList(Flight f, Passenger p, CrewMember c, int index) {
        this.f = f;
        this.p = p;
        this.c = c;
        this.index = index;
    }

    public ArrayList<Flight> getList() {
        return list;
    }

    public void setList(ArrayList<Flight> list) {
        this.list = list;
    }

    public ArrayList<Passenger> getPlist() {
        return plist;
    }

    public void setPlist(ArrayList<Passenger> plist) {
        this.plist = plist;
    }

    public ArrayList<CrewMember> getClist() {
        return clist;
    }

    public void setClist(ArrayList<CrewMember> clist) {
        this.clist = clist;
    }

    public Flight getF() {
        return f;
    }

    public void setF(Flight f) {
        this.f = f;
    }

    public Passenger getP() {
        return p;
    }

    public void setP(Passenger p) {
        this.p = p;
    }

    public CrewMember getC() {
        return c;
    }

    public void setC(CrewMember c) {
        this.c = c;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    @Override
    public void add() {
        do {
            f = new Flight();
            boolean check;
            do{
                check = false;
                f.createFlight();
                for (Flight fl : list)
                    if (f.getFlightNumber().equals(fl.getFlightNumber())){
                        check = true;
                        System.out.println("Flight number is dupplicated, create again!");
                        break;
                    }
            } while(check);
            list.add(f);
            System.out.println("Flight added successfully!");
        } while (Utils.getYesNo("Continue? (Yes/No): "));
    }

    @Override
    public int find(String code) {
        index = list.indexOf(new Flight(code));
        return index;
    }

    @Override
    public void delete() {
        this.index = find(Utils.getString("Enter flight number that you want to delete: "));
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
            System.out.println("No flight found!");
        }
    }

    @Override
    public void update() {
        this.index = find(Utils.getString("Enter the flight number that you want to update: "));
        if (index != -1) {
            f = list.get(index);
            f.updateFlight();
            list.set(index, f);
            System.out.println("Flight updated!");
            System.out.println("The result of the update: \n" + f.toString());
        } else {
            System.out.println("No flight found!");
        }
    }

    @Override
    public void search() {
        int choice;
        String text;
        int count = 0;
        
        try {
            do {
                System.out.println("\n----SEARCH FLIGHT MENU----");
                System.out.println("1 - Searching by departure city");
                System.out.println("2 - Searching by arrival city");
                System.out.println("3 - Searching by departure time");
                System.out.println("4 - Searching by arrival time");
                System.out.println("Other - Back to main menu \n");
                choice = Utils.getInt("Enter your choice: ");
                switch (choice) {
                    case 1: // Search by departure city.
                        text = Utils.getString("Enter the departure city: ");
                        for (Flight f : list) {
                            if (f.getDepartureCity()!= null && f.getDepartureCity().contains(text)) {
                                System.out.println(f.toString());
                                count ++;
                            }
                        }
                        if (count == 0)
                            System.out.println("No flight found!");
                        break;
                    case 2: // Search by arrival city
                        text = Utils.getString("Enter the arrival city: ");
                        for (Flight f : list) {
                            if (f.getDepartureCity() != null && f.getDepartureCity().equalsIgnoreCase(text)) {
                                System.out.println(f.toString());
                                count ++;
                                break;
                            }
                        }
                        if (count == 0)
                            System.out.println("No flight found!");
                        break;
                    case 3: // Search by departure time
                        text = Utils.getDate("Enter the departure time: ");
                        for (Flight f : list) {
                            if (f.getDepartureTime()!= null && f.getDepartureTime().contains(text)) {
                                System.out.println(f.toString());
                                count ++;
                            }
                        }
                        if (count == 0)
                            System.out.println("No flight found!");
                        break;
                    case 4: // Search by arrival time
                        text = Utils.getString("Enter the arrival time: ");
                        for (Flight f : list) {
                            if (f.getArrivalTime()!= null && f.getArrivalTime().contains(text)) {
                                System.out.println(f.toString());
                                count ++;
                            }
                        }
                        if (count == 0)
                            System.out.println("No flight found!");
                        break;
                }
            } while (choice >= 1 && choice <= 4);
        } catch (Exception e) {
        }
    }

    @Override
    public void readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("flight.dat");
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Flight>) ois.readObject();
            for (Flight f : list)
                System.out.println(f.toString());
        } catch (FileNotFoundException ex) {
            System.err.println("flight.dat not found!");
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
            fos = new FileOutputStream("flight.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            System.err.println("flight.dat Saved!");
        } catch (FileNotFoundException ex) {
            System.err.println("flight.dat not found!");
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
            fis = new FileInputStream("flight.dat");
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Flight>) ois.readObject();
            System.out.println("flight.dat Loaded!");
        } catch (FileNotFoundException ex) {
            System.err.println("flight.dat not found!");
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
    
    public static String generateNumber() {
        Set<Integer> generatedNumbers = new HashSet<>();
        Random random = new Random();
        int randomNumber;
        do {
            randomNumber = random.nextInt(1000000);
        } while (randomNumber < 100000 || generatedNumbers.contains(randomNumber));
        generatedNumbers.add(randomNumber);
        String s = String.valueOf(randomNumber);
        return s;
    }
    
    public int[] createArray(int num) {
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = i+1;
        }
        return array;
    }
    
    public void booking(){
        do{
            boolean check = true;
            int count = 0;
            search();
            do{
                String s = Utils.getString("Select a flight by flight number: ");
                for (Flight fl : list)
                    if (fl.getFlightNumber().equals(s)&&fl.getAvailableSeats()>0) {
                        System.out.println(fl.toString());
                        f = fl;
                        check = false;
                        count ++;
                    }
                if (count==0)
                    System.out.println("No flight found!");
            } while (check);
            p = new Passenger();
            p.createPassenger();
            String s = generateNumber();
            System.out.println("Your generated reservation id: " + s);
            p.setId(s);
            plist.add(p);
        }while(Utils.getYesNo("Continue?: "));
        f.setList(plist);
        this.index = find(f.getFlightNumber());
        list.set(index, f);
        plist.clear();
    }
    
    public void checkedIn(){
        String s = Utils.getString("Enter your reservation ID: ");
        int count = 0;
        for(Flight f: list)
            for(Passenger p : f.getList())
                if (p.getId().equals(s)){
                    count++;
                }
        if(count==0)
            System.out.print("ID is not available!");
        else {
            System.out.print("Available seats: ");
            int[] result = createArray(f.getAvailableSeats());
            for (int i = 0; i < result.length; i++)
                System.out.print("[" + result[i] + "] ");
        }
    }
    
    public void crewManagement(){
        c = new CrewMember();
        int choice;
        try{
            do{
                System.out.println("Crew Management Menu");
                System.out.println("1. Add Crew Member");
                System.out.println("2. Remove Crew Member");
                System.out.println("3. Modify Crew Member");
                System.out.print("Enter your choice: ");
                choice = Utils.getInt(null);
                switch (choice) {
                    case 1:
                        c.createCrewMember();
                        clist.add(c);
                        break;
                    case 2:
                        String s = Utils.getString("Crewmember ID: ");
                        int count = 0;
                        for(CrewMember c : clist)
                            if(c.getId().equals(s)){
                                clist.remove(c);
                                System.out.println("Crewmember removed!");
                                count ++;
                            }
                        if(count==0)
                            System.out.println("No crewmember found!");
                        break;
                    case 3:
                        c.updateCrewMember();
                        break;
                }
            }while (choice >= 0 && choice <= 3);
        } catch (Exception e) {
        }
    }
    
    public boolean admin(){
        if(Utils.getYesNo("Are you admin (Yes/No): "))
            return true;
        return false;
    }
}