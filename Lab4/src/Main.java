import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel a = new Hotel();
        byte choice;
        boolean flag = true;
        do{
            System.out.println("\n_____MENU_____");
            System.out.println("1. Search for flight");
            System.out.println("2. Searh for hotel");
            System.out.println("3. Book hotel's room");
            System.out.println("4. Cancel hotel's room");
            System.out.println("Others. Quit");
            System.out.print("Your option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextByte();
            switch(choice){
                case 1:
                    System.out.print("Flight: "+a.searchFlight());
                    break;
                case 2:
                    System.out.print("Hotel: "+a.searchHotel());
                    break;
                case 3:
                    a.bookRoom();
                    break;
                case 4:
                    System.out.print("Enter room number: ");
                    int room = sc.nextInt();
                    a.cancelRoom(room);
                    break;
                default:
                    System.out.println("Goodbye!");
                    flag = false;
                    break;
            }
        }while(flag==true);
    }
}
