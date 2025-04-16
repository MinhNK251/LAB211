import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Movie1 movie = new Movie1();
        byte choice;
        boolean flag = true;
        do{
            System.out.println("\n_____MENU_____");
            System.out.println("1. Add new rating");
            System.out.println("2. Display movie's ratings");
            System.out.println("Others. Quit");
            System.out.print("Your option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextByte();
            switch(choice){
                case 1:
                    movie.addRating();
                    break;
                case 2:
                    movie.showRatings();
                    break;
                default:
                    System.out.println("Goodbye!");
                    flag = false;
                    break;
            }
        }while(flag==true);
    }
}
