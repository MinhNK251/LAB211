import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MusicLibrary {
    List musicList = new LinkedList();
    Scanner sc = new Scanner(System.in);

    public MusicLibrary() {
    }

    public void addSong(){
        System.out.print("\nSong's name: ");
        String name = sc.nextLine();
        musicList.add(name);
        System.out.println("Song added successfully");
    }
    
    public void removeSong () {
        if (musicList.isEmpty()){
            System.out.println("\nMusic list is empty ");
            return;
        }
        System.out.print("\nSong's index: ");
        int index = sc.nextInt();
        musicList.remove(index);
        System.out.println("Song removed successfully");
    }
    
    public void showAll(){
        System.out.println("\n_____Music List_____");
        for(int i = 0; i < musicList.size(); i++) {
            System.out.println(i+". "+musicList.get(i));
        }
    }
    
    public static void main(String[] args) {
        MusicLibrary music = new MusicLibrary();
        byte choice;
        do{
            System.out.println("\n_____MENU_____");
            System.out.println("1. Add song");
            System.out.println("2. Remove song");
            System.out.println("3. Display song list");
            System.out.println("Others. Quit");
            System.out.print("Your option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextByte();
            switch(choice){
                case 1:
                    music.addSong();
                    break;
                case 2:
                    music.removeSong();
                    break;
                case 3:
                    music.showAll();
                    break;
                default:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }
        }while(choice<4&&choice>0);
    }
}