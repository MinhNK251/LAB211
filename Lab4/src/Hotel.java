import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    List<Hotel> hotelList = new ArrayList();
    Scanner sc = new Scanner(System.in);
    private String flight;
    private int room;
    private String hotel;
    final String f1 = "A";
    final String f2 = "B";
    final String f3 = "C";
    final String h1 = "A";
    final String h2 = "B";
    final String h3 = "C";

    public Hotel() {
    }

    public Hotel(String flight, int room, String hotel) {
        this.flight = flight;
        this.room = room;
        this.hotel = hotel;
    }
    
    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
    //</editor-fold>
    
    public String searchFlight () {
        System.out.println("Enter destination: ");
        String destination = sc.nextLine();
        if (destination.equalsIgnoreCase("Hawaii"))
             return f1;
        if (destination.equalsIgnoreCase("Tokyo"))
             return f2;
        if (destination.equalsIgnoreCase("Viet Nam"))
             return f3;
        System.out.println("No flights available");
        return null;
    }
    
    public String searchHotel (){
        System.out.println("Enter Hotel (A, B, C): ");
        hotel= sc.nextLine();
        if (hotel.equalsIgnoreCase(h1))
             return h1;
        if (hotel.equalsIgnoreCase(h2))
             return h2;
        if (hotel.equalsIgnoreCase(h3))
             return h3;
        System.out.println("No hotels available");
        return null;
    }
    
    public void bookRoom() {
        Hotel a = new Hotel();
        do{
            System.out.println("Enter hotel name: ");
            hotel = sc.nextLine();
        } while(!(hotel.equalsIgnoreCase(h1) || hotel.equalsIgnoreCase(h2) || hotel.equalsIgnoreCase(h3)));
        do{
            System.out.println("Enter room number: ");
            room = sc.nextInt();
        } while(checkRoom(room));
        hotelList.add(a);
    }
    
    public void cancelRoom(int roomNumber) {
        if (hotelList.isEmpty()){
            System.out.println("List is empty");
            return;
        }
        for (Hotel a: hotelList)
            if (a.room == roomNumber)
                hotelList.remove(a);
    }
    
    public Boolean checkRoom(int roomNumber){
        for (Hotel a: hotelList)
            if (a.room == roomNumber)
                return false;
        return true;
    }
     
    public void show(){
        int i =0;
        for (Hotel a: hotelList){
            i++;
            System.out.println(i+". Hotel: "+a.hotel+"\n   Room: "+a.room);
        }
    }
}
